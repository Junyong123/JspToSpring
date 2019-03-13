package kr.or.ddit.posting.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.attach.service.IAttachService;
import kr.or.ddit.posting.model.PostingVO;
import kr.or.ddit.posting.service.IPostingService;
import kr.or.ddit.reply.model.ReplyVO;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.PartUtil;
import kr.or.ddit.util.model.PageVO;

@Controller
@MultipartConfig(maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
public class PostingControllerSpring {
	
	@Resource(name="postingService")
	private IPostingService postingService;
	
	@Resource(name="attachService")
	private IAttachService attachService;
	
	@Resource(name="replyService")
	private IReplyService replyService;
	
	@RequestMapping(path={"/posting"},method={RequestMethod.GET})
	public String Posting(PageVO pageVO,@RequestParam("board_num")String board_num_str,Model model){
		//해당 게시판 번호를 파라미터로 받기
		Integer board_num = Integer.parseInt(board_num_str);

		//해당 게시판번호를 requset 속성에 설정
		model.addAttribute("board_num", board_num);
		
		//계층형 게시글을 조회한후 posting_level을 update해줘야함(페이징처리된 계층형 쿼리를 조회하려면 필수)
		//검색한 level로 posting_level을 세팅
		List<PostingVO> postingHierar = postingService.selectHierar(board_num);
		
		int resultUpdate = 0;
		for(PostingVO postingVO : postingHierar){
			String posting_level = postingVO.getPosting_level();
			
			postingVO.setPosting_level(posting_level);
			
			resultUpdate += postingService.updateLevel(postingVO);
		}
		
		//page, pageSize에 해당하는 파라미터 받기 ==> pageVO
		//단, 파라미터가 없을경우 page : 1, pageSize : 10
		int page = Integer.toString(pageVO.getPage()) == null ? 1 : pageVO.getPage();
		int pageSize = Integer.toString(pageVO.getPageSize()) == null ? 10 : pageVO.getPageSize();
		pageVO = new PageVO(page, pageSize);
		
		//postingService 객체를 이용 postingPaging 조회
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_num", board_num);
		map.put("page", pageVO.getPage());
		map.put("pageSize", pageVO.getPageSize());
		
		//해당 게시판에 해당하는 게시글을 계층형으로 페이징 조회
		Map<String, Object> resultMap = postingService.selectPostingPaging(map, board_num);
		
		//게시글 리스트
		List<PostingVO> postingList = (List<PostingVO>) resultMap.get("postingList");
		
		//게시글 수
		int postingCnt = (int)resultMap.get("postingCnt");
		//마지막페이지 구하기
		int lastPage = postingCnt/pageSize + (postingCnt%pageSize > 0 ? 1 : 0);
//				int lastPage = (int)Math.ceil((postingCnt*1.0)/pageSize);
		
		//마지막페이지가 포함된 시작페이지 구하기
		int lastPageStartPage = ((lastPage - 1) / 10) * 10 + 1;
		//시작 페이지 구하기
		//10의 배수로 끝나는 페이지는 10으로 나누면 몫이 증가하기 때문에 - 1 한후
		//10으로 나눈 몫을 다시 10으로 곱해주고 + 1해주면 예시로 12페이지든 13페이지든 시작이 11페이지가 됨
		int startPage = ((page - 1) / 10) * 10 + 1; 
		//마지막 페이지 구하기
		//시작페이지 + 10 하고 -1 해주면 예시로 12페이지든 13페이지든 끝이 20페이지가 됨
		int endPage = startPage + 10 - 1;
		
		//request객체에 조회된 결과를 속성으로 설정
		model.addAttribute("postingList", postingList);
		model.addAttribute("postingCnt", postingCnt);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("page", page);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("lastPageStartPage", lastPageStartPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
	
		return "/posting/posting";
	}
	
	@RequestMapping(path={"/postingDetail"},method={RequestMethod.GET})
	public String postingDetail(@RequestParam("posting_num")String posting_num_str,Model model){
		//새글 등록한후 상세화면으로 넘어올때 posting_num을 파라미터로 받아서 해당 게시글 조회
		Integer posting_num = Integer.parseInt(posting_num_str);
		
		//해당 게시글 조회한후 request 속성에 설정
		PostingVO postingVO = postingService.selectPosting(posting_num);
		model.addAttribute("postingVO", postingVO);
		
		//해당 게시글의 첨부파일 조회한후 있으면 속성에 설정
		List<AttachVO> attachList = attachService.selectAttachList(posting_num);
		if(attachList != null){
			model.addAttribute("attachList", attachList);
		}
		
		//해당 게시글의 댓글 조회한후 있으면 속성에 설정
		List<ReplyVO> replyList = replyService.getAllReply(posting_num);
		if(replyList != null){
			model.addAttribute("replyList", replyList);
		}
		return "/posting/postingDetail";
	}
	
	@RequestMapping(path={"/postingInsert"},method={RequestMethod.GET})
	public String postingInsertGET(@RequestParam("board_num")String board_num,
							@RequestParam("parentposting_num")String parentposting_num,Model model){
		// board_num을 파라미터로 받아 request 속성에 설정
		model.addAttribute("board_num", board_num);
		
		//답글인경우 부모글번호를 받아서 request 속성에 설정
		if(parentposting_num != null){
			model.addAttribute("parentposting_num", parentposting_num);
		}
		
		//게시글 등록페이지로 forward
		return "/posting/postingInsert";
	}
	
	@RequestMapping(path={"/postingInsert"},method={RequestMethod.POST})
	public String postingInsertPOST(@RequestParam("parentposting_num")String parentposting_num_str,
			@RequestParam("board_num")String board_num_str,
			@RequestParam("posting_title")String posting_title,
			@RequestParam("posting_content")String posting_content,UserVO userVO,
			HttpServletRequest request) throws IOException, ServletException{
		
		//부모글 번호 세팅
		Integer parentposting_num = null;
		if(parentposting_num_str == null || parentposting_num_str.equals("")){
			parentposting_num = null;
		}else{
			parentposting_num = Integer.parseInt(parentposting_num_str);
		}
		
		//게시글 세팅
		Integer board_num = Integer.parseInt(board_num_str);
		String posting_userid = userVO.getUserId();
		PostingVO postingVO = new PostingVO(board_num, posting_title, posting_content, posting_userid, parentposting_num);
		
		int result1 = postingService.insertPosting(postingVO);
		int result2 = 0;
		
		//파일이름과 저장경로 초기화
		String filename = "";
		String realFilename = "";
		
		//첨부파일
		Collection<Part> parts = request.getParts();
		
		for(Part part : parts){
			if(part.getName().equals("attach")){
				
				//첨부파일을 등록한 경우
				if(part.getSize() > 0){
					String contentDisposition = part.getHeader("Content-Disposition");
					filename = PartUtil.getFileNameFromPart(contentDisposition);
					realFilename = "d:\\attach\\" + UUID.randomUUID().toString();
					
					//디스크에 기록(d:\attachfile\ + realFileName)
					part.write(realFilename);
					part.delete();
					
					//파일명, uuid(저장경로) 세팅
					AttachVO attachVO = new AttachVO();
					attachVO.setFilename(filename);
					attachVO.setRealfilename(realFilename);
					attachVO.setPosting_num(postingVO.getPosting_num()); //insert된 시퀀스 값가져와야함 
					
					result2 += attachService.insertAttach(attachVO);
				}
				
				//첨부파일을 올리지 않은 경우 filename, realFilename 모두 공백(위에서 초기화한값)
			}
		}
		
		
		//정상 입력(성공)
		if(result1 == 1){
			//db에서 데이터를 조작하는 로직을 처리할때는 forward가 아니라 redirect를 사용해야함(새로고침시 최초요청 url로 다시 이동하기때문에)
			//redirect는 ContextPath를 써줘야하며 redirect는 get방식임
			return "redirect:/postingDetail?posting_num=" + postingVO.getPosting_num();
		}
		//정상 입력(실패)
		else{
			return "/posting/postingDetail";
		}
	}
	
	@RequestMapping(path={"/postingUpdate"},method={RequestMethod.GET})
	public String PostingUpdateGET(@RequestParam("type")String type,
			@RequestParam("posting_num")String posting_num_str,Model model){
		
		Integer posting_num = Integer.parseInt(posting_num_str);
		
		//파라미터로 받은 게시글 번호로 해당 게시글 조회
		PostingVO postingVO = postingService.selectPosting(posting_num);
		
		//해당 게시글의 첨부파일 조회
		List<AttachVO> attachList = attachService.selectAttachList(posting_num);
		
		int result = 0;
		//타입이 1이면 수정
		if(type.equals("1")){
			//수정시 request 속성에 설정한후 postingUpdate.jsp로 forward
			model.addAttribute("postingVO", postingVO);
			model.addAttribute("attachList", attachList);
			return "/posting/postingUpdate";
		}
		//타입이 2이면 삭제
		else if(type.equals("2")){
			PostingVO posting = new PostingVO();
			posting.setPosting_title(postingVO.getPosting_title());
			posting.setPosting_content(postingVO.getPosting_content());
			posting.setDelete_exist("Y");
			posting.setPosting_num(postingVO.getPosting_num());
			
			result = postingService.updatePosting(posting);
			
			//정상입력(성공) - 리다이렉트는 get방식으로 게시판번호를 넘겨줘야함
			if(result == 1){
				return "redirect:/posting?board_num=" + postingVO.getBoard_num();
			}
			//실패 - 다시 상세조회를 가야하므로 게시글번호를 넘겨줘야함
			else{
				return "/posting/postingDetail?posting_num="+ posting_num;
			}
		}
		return posting_num_str;
	}
	
	@RequestMapping(path={"/postingUpdate"},method={RequestMethod.POST})
	public String PostingUpdatePOST(@RequestParam("posting_num")String posting_num_str,
			@RequestParam("posting_title")String posting_title,
			@RequestParam("posting_content")String posting_content,
			@RequestParam("attach_num")String attach_num_str,HttpServletRequest request
			) throws IOException, ServletException{
		Integer posting_num = Integer.parseInt(posting_num_str);
		
		//해당 게시글번호로 게시글 조회
		PostingVO postingVO = postingService.selectPosting(posting_num);
		
		//게시글 세팅
		postingVO.setPosting_title(posting_title);
		postingVO.setPosting_content(posting_content);
		
		int result = postingService.updatePosting(postingVO);
		int result2 = 0;
		
		//기존의 첨부파일 삭제
		if(attach_num_str != null){
			Integer attach_num = Integer.parseInt(attach_num_str);
			attachService.deleteAttach(attach_num);
		}
		
		//파일이름과 저장경로 초기화
		String filename = "";
		String realFilename = "";
		
		//첨부파일
		Collection<Part> parts = request.getParts();
		
		for(Part part : parts){
			if(part.getName().equals("attach")){
				
				//첨부파일을 등록한 경우
				if(part.getSize() > 0){
					String contentDisposition = part.getHeader("Content-Disposition");
					filename = PartUtil.getFileNameFromPart(contentDisposition);
					realFilename = "d:\\attach\\" + UUID.randomUUID().toString();
					
					//디스크에 기록(d:\attachfile\ + realFileName)
					part.write(realFilename);
					part.delete();
					
					//파일명, uuid(저장경로) 세팅
					AttachVO attachVO = new AttachVO();
					attachVO.setFilename(filename);
					attachVO.setRealfilename(realFilename);
					attachVO.setPosting_num(postingVO.getPosting_num()); //insert된 시퀀스 값가져와야함 
					
					result2 += attachService.insertAttach(attachVO);
				}
				
				//첨부파일을 올리지 않은 경우 filename, realFilename 모두 공백(위에서 초기화한값)
			}
		}
		
		
		//정상입력(성공) - 해당 게시글 상세조회로 넘어감
		if(result == 1){
			return "redirect:/postingDetail?posting_num=" +postingVO.getPosting_num();
		}
		//실패 - 원래 화면을 다시 보여줌
		else{
			return "/postingUpdate?type=1&posting_num=" + postingVO.getPosting_num();
		}
	}
	
}

package kr.or.ddit.reply.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.reply.model.ReplyVO;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.user.model.UserVO;

@Controller
public class ReplyControllerSpring {
	
	@Resource(name="replyService")
	private IReplyService replyService;
	
	
	@RequestMapping(path="/reply",method=RequestMethod.POST)
	public String reply(@RequestParam("type")String type,
			@RequestParam("posting_num")String posting_num_str,
			@RequestParam("reply_content")String reply_content,
			@RequestParam("reply_num")String reply_num_str,
			HttpServletRequest request
		){
		//타입과 게시글번호, 게시글내용을 파라미터로 받고, session에서 userVO 객체를 가져옴
		Integer posting_num = Integer.parseInt(posting_num_str);
		UserVO userVO = (UserVO) request.getSession().getAttribute("userVO");
		
		int result = 0;
		//타입이 1이면 등록
		if(type.equals("1")){
			ReplyVO replyVO = new ReplyVO();
			replyVO.setReply_content(reply_content);
			replyVO.setPosting_num(posting_num);
			replyVO.setReply_userid(userVO.getUserId());
			replyVO.setReply_admin(userVO.getUserId());
			result = replyService.insertReply(replyVO);
		}
		//타입이 2이면 삭제
		else if(type.equals("2")){
			//댓글번호를 파라미터로 받아서 댓글 조회
			Integer reply_num = Integer.parseInt(reply_num_str);
			ReplyVO replyVO = replyService.selectReply(reply_num);
			
			replyVO.setDelete_exist("Y");
			result = replyService.updateReply(replyVO);
		}
		
		//result 결과가 성공이든 실패든 해당 게시글 상세화면으로 넘어감
		//정상입력(성공) - 해당 게시글 상세조회로 넘어감
		return "redirect:/postingDetail?posting_num="+posting_num;
	}
	
	@RequestMapping(path="/replyUpdate",method=RequestMethod.GET)
	public String replyUpdateGET(@RequestParam("reply_num")String reply_num_str,Model model){
		Integer reply_num = Integer.parseInt(reply_num_str);
		ReplyVO replyVO = replyService.selectReply(reply_num);
		
		//댓글을 request 속성에 설정
		model.addAttribute("replyVO", replyVO);
		
		return "/reply/replyUpdate";
	}
	
	@RequestMapping(path="/replyUpdate",method=RequestMethod.POST)
	public String replyUpdatePOST(@RequestParam("reply_num")String reply_num_str,
				@RequestParam("reply_content")String reply_content
			){
		//댓글번호를 파라미터로 받아서 댓글 조회
		Integer reply_num = Integer.parseInt(reply_num_str);
		ReplyVO replyVO = replyService.selectReply(reply_num);
		
		replyVO.setReply_content(reply_content);
		
		int result = replyService.updateReply(replyVO);;
		
		//게시글 번호를 조회한후
		Integer posting_num = replyVO.getPosting_num();
		
		//성공시 게시글 상세화면으로
		if(result == 1){
			return "redirct:/postingDetail?posting_num="+ posting_num;
		}
		//실패시
		else{
			return "/replyUpdate?reply_num=" + reply_num;
		}
	}
}

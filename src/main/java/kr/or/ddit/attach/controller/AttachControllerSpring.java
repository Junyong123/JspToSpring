package kr.or.ddit.attach.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.attach.service.IAttachService;
import kr.or.ddit.posting.model.PostingVO;
import kr.or.ddit.posting.service.IPostingService;

@Controller
public class AttachControllerSpring {

	@Resource(name="attachService")
	private IAttachService attachService;
	
	@Resource(name="postingService")
	private IPostingService postingService;
	
	@RequestMapping(path="/attach",method=RequestMethod.GET)
	public void attach(@RequestParam("attach_num")String attach_num_str,HttpServletResponse response,HttpServletRequest request) throws Exception{
		response.setHeader("Content-Disposition", "attachment; filename=profile.png");
		response.setContentType("application/octet-stream");
//		response.setContentType("image");
		
		Integer attach_num = Integer.parseInt(attach_num_str);
		AttachVO attachVO = attachService.selectAttach(attach_num);
		
		response.setContentType("image/png");
		
		FileInputStream fis;
		if(attachVO != null && attachVO.getRealfilename() != null){
			fis = new FileInputStream(new File(attachVO.getRealfilename()));
		}
		
		else{
			ServletContext application = request.getServletContext();
			String noimgPath = application.getRealPath("/upload/noimg.png");
			fis = new FileInputStream(new File(noimgPath));
		}
		
		ServletOutputStream sos = response.getOutputStream();
		byte[] buff = new byte[512];
		int len = 0;
		while((len = fis.read(buff)) > -1){
			sos.write(buff);
		}
		
		sos.close();
		fis.close();
	}
	
	@RequestMapping(path="/attachDelete",method=RequestMethod.GET)
	public String attachDelete(@RequestParam("posting_num")String posting_num_str,
							   @RequestParam("attach_num")String attach_num_str, Model model){
		Integer posting_num = Integer.parseInt(posting_num_str);
		Integer attach_num = Integer.parseInt(attach_num_str);
		
		//기존의 첨부파일 삭제
		attachService.deleteAttach(attach_num);
		
		//해당 게시글의 첨부파일 조회
		List<AttachVO> attachList = attachService.selectAttachList(posting_num);
		
		//파라미터로 받은 게시글 번호로 해당 게시글 조회
		PostingVO postingVO = postingService.selectPosting(posting_num);
		
		//수정시 request 속성에 설정한후 postingUpdate.jsp로 forward
		model.addAttribute("postingVO", postingVO);
		model.addAttribute("attachList", attachList);
		
		return "/posting/postingUpdate";
	}
	
	
}

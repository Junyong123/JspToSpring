package kr.or.ddit.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.KISA_SHA256;

@Controller
public class LoginControllerSpring {
	
	@Resource(name="userService")
	private IUserService userService;
	
	@Resource(name="boardService")
	private IBoardService boardService;
	
	@RequestMapping(path={"/login"},method={RequestMethod.GET})
	public String LoginGet(){
		return "/login/login";
	}
	
	@RequestMapping(path={"/login"},method={RequestMethod.POST})
	public String LoginPost(@RequestParam("userId")String userId,@RequestParam("pass")String pass,Model model,HttpServletRequest request){
		
		//디비에 사용자가 존재하는지 조회
		UserVO userVO = userService.selectUser(userId);
		
		//db정보와 사용자 파라미터 아이디와 패스워드가 일치하는경우 -> main.jsp
		if(userVO != null && userVO.getUserId().equals(userId) && userVO.getPass().equals(KISA_SHA256.encrypt(pass))){
			HttpSession session = request.getSession();
			session.setAttribute("userVO", userVO);
			
			model.addAttribute("boardList", boardService.getAllBoard());
			
			// localhost/main.jsp으로 보냄
			return "mainTiles";
		}
		//db정보와 사용자 파라미터 아이디와 패스워드가 일치하지않는경우 -> login.jsp
		return "/login/login";
	}
}

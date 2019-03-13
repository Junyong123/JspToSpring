package kr.or.ddit.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutControllerSpring {

	@RequestMapping(path={"logout"},method={RequestMethod.GET})
	public String logout(Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("userVO");
		
		return "/login/login";
	}
}

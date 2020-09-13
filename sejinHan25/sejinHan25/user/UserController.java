package sejinHan25.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	@Autowired 
	private UserService userService;
	 
	
	@RequestMapping(value="/user/loginForm", method=RequestMethod.GET)
	public String loginForm() {
		return "/user/loginForm";
	}
	
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam String id,@RequestParam String pwd,HttpSession session) {
		System.out.println("컨트롤러 아이디"+id);
		System.out.println("컨트롤러 비밀번호"+pwd);
		UserDTO userDTO = userService.login(id,pwd);
		String result = "";
		if(userDTO != null) {
			result="success";
			session.setAttribute("memId", userDTO.getId());
		}else {
			result="fail";
		}
		return result;
	}
	
	@RequestMapping(value="/user/welcome",method=RequestMethod.GET)
	public String welcome() {
		return "/user/welcome";
	}
	
	@RequestMapping(value="/user/signUpForm",method=RequestMethod.GET)
	public String signUpForm() {
		return "/user/signUpForm";
	}
	
	
}

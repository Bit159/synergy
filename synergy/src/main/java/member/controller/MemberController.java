package member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import member.bean.MemberDTO;
import member.service.MemberService;

@Controller
public class MemberController {
	@Autowired(required=false)
	private MemberService memberService;

	@RequestMapping(value="/member/loginForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String loginForm() {
		return "/member/loginForm";
	}
	
	@RequestMapping(value="/member/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam String id,@RequestParam String pwd,HttpSession session) {
		System.out.println("컨트롤러 아이디"+id);
		System.out.println("컨트롤러 비밀번호"+pwd);
		MemberDTO memberDTO = memberService.login(id,pwd);
		String result = "";
		if(memberDTO != null) {
			result="success";
			session.setAttribute("memId", memberDTO.getId());
			System.out.println("잘받았음 "+memberDTO.getName());
		}else {
			result="fail";
		}
		return result;
	}
	@RequestMapping(value="/member/welcome",method=RequestMethod.GET)
	public String welcome() {
		return "/member/welcome";
	}
	@RequestMapping(value="/member/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "/member/loginForm";
	}
	
	@RequestMapping(value="/member/signUpForm",method=RequestMethod.POST)
	public String signUpForm() {
		return "/member/signUpForm";
	}
}

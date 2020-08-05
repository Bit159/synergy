package member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	@RequestMapping(value="/all/loginForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String loginForm() {
		return "/all/loginForm";
	}
	
//	@RequestMapping(value="/member/login",method=RequestMethod.POST)
//	public void login(@RequestParam Map<String, String> map, HttpSession session) {
//		memberService.login(map);
//		String result = "";
//		if(memberDTO != null) {
//			return "/member/welcome";
//		}else {
//			return "/member/loginForm";
//		}
//	}
	@RequestMapping(value="/member/welcome",method=RequestMethod.GET)
	public String welcome() {
		return "/member/welcome";
	}
//	@RequestMapping(value="/member/logout",method=RequestMethod.GET)
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "/member/loginForm";
//	}
	@RequestMapping(value="/member/signUpForm",method=RequestMethod.GET)
	public String signUpForm() {
		return "/member/signUpForm";
	}
	@GetMapping(value="/all/joinForm")
	public String joinForm() {
		return "/all/joinForm";
	}
	 @PostMapping(value="/all/join")
	 @ResponseBody
	 public void join(@RequestParam Map<String, String> map) {
		 memberService.join(map);
	 }
	 
	 @GetMapping(value="/admin/adminPage")
	 public String adminPage() {
		 return "/admin/adminPage";
	 }
	
}

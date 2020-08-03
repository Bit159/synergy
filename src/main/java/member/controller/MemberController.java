package member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import member.bean.MemberDTO;
import member.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("index")
	public String index() {
		return "/all/index";
	}

	@RequestMapping(value = "/all/loginForm", method = RequestMethod.GET)
	public String loginForm() {
		return "/all/loginForm";
	}

	@RequestMapping(value = "/all/joinForm", method = RequestMethod.GET)
	public String joinForm() {
		return "/all/joinForm";
	}

	@RequestMapping(value = "/member/myPage", method = RequestMethod.GET)
	public String myPage() {
		return "/member/myPage";
	}

	@RequestMapping(value = "/member/myPage_Update", method = RequestMethod.GET)
	public String myPageUpdate() {
		return "/member/myPage_Update";
	}

	@RequestMapping(value = "/all/login", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestParam Map<String, String> map, HttpSession session) {
		System.out.println(map.get("id") + "," + map.get("pw"));

		MemberDTO memberDTO = memberService.login(map);
		// System.out.println(memberDTO);
		String loginResult = "fail";

		
		 if(memberDTO != null) { 
			 session.setAttribute("memId", memberDTO.getId());
			 session.setAttribute("memNickname", memberDTO.getNickname());
			 session.setAttribute("memEmail", memberDTO.getEmail());
			 System.out.println(memberDTO.getId()); 
			
			 loginResult = "ok";
		 }
		 

		System.out.println(loginResult);
		return loginResult;
	}

	@RequestMapping(value = "/all/join", method = RequestMethod.POST)
	@ResponseBody
	public void join(@RequestParam Map<String, Object> map) {
		/*
		 * Set set = map.keySet(); Iterator iterator = set.iterator();
		 * while(iterator.hasNext()){ String key = (String) iterator.next();
		 * System.out.println("KEY : " + key); // Key2 , Key1, Key5, Key4, Key3 }
		 */
		memberService.join(map);

	}
}

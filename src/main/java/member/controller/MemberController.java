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
	@Autowired
	private MemberService memberService;
	
	@GetMapping("index")
	public String index() {
		return "/all/index";
	}

	@GetMapping("/all/loginForm")
	public String loginForm() {
		return "/all/loginForm";
	}

	@GetMapping("/all/joinForm")
	public String joinForm() {
		return "/all/joinForm";
	}
	
	@GetMapping("/all/joinus")
	public String joinus() {
		return "/all/joinus";
	}

	@GetMapping("/member/myPage")
	public String myPage() {
		return "/member/myPage";
	}

	@GetMapping("/member/myPage_Update")
	public String myPageUpdate() {
		return "/member/myPage_Update";
	}

	@PostMapping("/all/login")
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

	@PostMapping("/all/join")
	@ResponseBody
	public void join(@RequestParam Map<String, String> map) {
		/*
		 * Set set = map.keySet(); Iterator iterator = set.iterator();
		 * while(iterator.hasNext()){ String key = (String) iterator.next();
		 * System.out.println("KEY : " + key); // Key2 , Key1, Key5, Key4, Key3 }
		 */
		
		System.out.println(map.get("username"));
		memberService.join(map);

	}
	
	@GetMapping("/admin/admin_index")
	public String admin_index() {
		return "/admin/admin_index";
	}
	
	@PostMapping("/logout")
	public void logout() {
		
	}

}

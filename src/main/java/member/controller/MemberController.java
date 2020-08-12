package member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import member.bean.MemberDTO;
import member.service.MemberService;

@Controller
public class MemberController{
	@Autowired
	private MemberService memberService;
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	@Autowired
	private OAuth2Parameters googleOAuth2Parameter;
	
	@GetMapping("index")
	public String index() {
		return "/all/index";
	}

	@GetMapping("/all/loginForm")
	public String loginForm(Model model) {
		OAuth2Operations oauth2Operations = googleConnectionFactory.getOAuthOperations();
		String url = oauth2Operations.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameter);
		model.addAttribute("google_url", url);
		
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

	/*@PostMapping("/all/login")
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
	}*/

	@PostMapping("/join")
	public String join(@RequestParam Map<String, String> map) {
		/*
		 * Set set = map.keySet(); Iterator iterator = set.iterator();
		 * while(iterator.hasNext()){ String key = (String) iterator.next();
		 * System.out.println("KEY : " + key); // Key2 , Key1, Key5, Key4, Key3 }
		 */
		
		memberService.join(map);
		
		return "redirect:/all/loginForm";
		
		/*
		 redirect:/ 방식은 주소를  url 요청을 다시 하는것.
		 return 주소 방식은 단순히 해당하는 view를 보여주는 것.
		 */

	}
	
	@GetMapping("/admin/admin_index")
	public String admin_index() {
		return "/admin/admin_index";
	}
	
	/*@PostMapping("/logout")
	public void logout() {
		
	}*/
	
	//================================구글 로그인 콜백메소드
	@RequestMapping(value="/googleLogin", method= {RequestMethod.GET, RequestMethod.POST})
	public String googleCallBack(@RequestParam String code) {
		
		return "redirect:/index";
	}
	
	@PostMapping("/all/checkMember")
	@ResponseBody
	public String checkMember(@RequestParam String username, RedirectAttributes redirectAtt, HttpSession session) {
		MemberDTO memberDTO = memberService.checkMember(username);
		redirectAtt.addAttribute("username", username);
		
		if(memberDTO != null) {
			return "ok";
			
		}else {
			return "none";
		}
		
	}
	
	@GetMapping("/all/addInfoForm")
	public String addInfoForm(@RequestParam String username, Model model) {	
		model.addAttribute("username", username);
		return "/all/addInfoForm";
	}
	
	@PostMapping("all/addInfo")
	public String addInfo(@ModelAttribute MemberDTO memberDTO) {
		Map<String, String> map = new HashedMap<String, String>();
		map.put("username", memberDTO.getUsername());
		map.put("password", memberDTO.getPassword());
		map.put("nickname", memberDTO.getNickname());
		map.put("birthYear", memberDTO.getBirthYear());
		
		memberService.join(map);
		
		return "/all/index";
	}
	
	@GetMapping("/member/chatting")
	public String chatting() {
		return "/member/chatting";
	}


}
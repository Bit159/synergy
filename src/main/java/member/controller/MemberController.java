package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
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
	
	//============================== 메인페이지
	
	@GetMapping("index")
	public String index() {
		return "/all/index";
	}

	@GetMapping(value="/all/welcome")
	public String welcome() {
		return "/all/welcome";
	}

	@GetMapping("/admin/admin_index")
	public String admin_index() {
		return "/admin/admin_index";
	}
	
	
	//============================== 로그인
	
	@GetMapping("/all/loginForm")
	public String loginForm(Model model) {
		OAuth2Operations oauth2Operations = googleConnectionFactory.getOAuthOperations();
		String url = oauth2Operations.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameter);
		model.addAttribute("google_url", url);

		return "/all/loginForm";
	}

	//================================구글 로그인 콜백메소드
	@RequestMapping(value="/googleLogin", method= {RequestMethod.GET, RequestMethod.POST})
	public String googleCallBack(@RequestParam String code, RedirectAttributes attr) {
		//여기서 이메일 값을 받아올 수 없어서 따로 처리를 한다.
		attr.addAttribute("password", "bitcamp159");
		
		return "redirect:/all/loginForm";

	}
	//============================== 회원가입
	
	@GetMapping("/all/joinForm")
	public String joinForm() {
		return "/all/joinForm";
		
	}

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
	
	@PostMapping("/all/addInfoForm")
	public String addInfoForm(@RequestParam String email, Model model) {	
		model.addAttribute("username", email);
		return "/all/addInfoForm";
	}
	
	@PostMapping("all/addInfo")
	public String addInfo(@RequestParam Map<String, String> map) {
		map.put("password", "bitcamp159");
		memberService.join(map);
		return "/all/index";
	}
	
	@PostMapping("/all/checkMember")
	@ResponseBody
	public String checkMember(@RequestParam String username) {
		MemberDTO memberDTO = memberService.checkMember(username);
		System.out.println(username);
		if(memberDTO != null) {
			return "ok";
			
		}else {
			return "none";
		}
		
	}
	
	//================================ 마이페이지
	
	@ResponseBody
	@RequestMapping(value="/member/myPage",method=RequestMethod.POST)
	public ModelAndView myPage(@RequestParam String username){
		MemberDTO memberDTO = memberService.checkMember(username);
		//System.out.println(memberDTO.getUsername());
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDTO", memberDTO);
		mav.setViewName("/member/myPage");
		
		return mav;
	}
	
	  @RequestMapping(value="/member/revise",method=RequestMethod.POST) 
	  public String revise(@RequestParam String username, String password, String nickname) {
		  
	  System.out.println(username);
	  System.out.println(password);
	  System.out.println(nickname);
	  
		
	  
		 if(password == null || password == ""){ 
			 System.out.println(username);
			 System.out.println(password);
			 System.out.println(nickname);
			 
			 Map<String, String> map = new HashMap<String, String>();
		 	 map.put("username", username);
		 	 map.put("nickname", nickname);
		 	 
		 	 memberService.nicknameRevice(map);
		 	 
		 	 return "/member/welcome"; 
		 	 
		 }else{
	  
			  Map<String, String> map = new HashMap<String, String>();
			  map.put("username", username);
			  map.put("password", password);
			  map.put("nickname", nickname);
			  
			  memberService.revise(map);
			  
			  return "/member/welcome"; 
		 }
	  
	 
	}
	
	@RequestMapping(value="/member/withdrawal",method=RequestMethod.POST) //회원탈퇴
	public String withdrawal(@RequestParam String username) {
		memberService.withdrawal(username);
		return "redirect:/all/loginForm"; 
		
	}

	//============================== 게시판
	
	@GetMapping(value="/member/cardBoard")
	 public String card() {
		 return "/member/cardBoard";
	 }
	 
	 @GetMapping(value="/member/recruitGroup")
	 public String recruitRegist() {
		 return "/member/recruitGroup";
	 }
	 
	 @GetMapping(value="/member/createGroup")
	 public String createGroup() {
		 return "/member/createGroup";
	 }
	
	//============================== 채팅
	
	@GetMapping("/member/chatting")
	public String chatting() {
		return "/member/chatting";
	}
	
	//==================================== 지역 자동완성
	
	 @GetMapping(value="/member/autocomplete")
	 @ResponseBody
	 public ModelAndView autocomplete() {
		 ModelAndView mav = new ModelAndView();
		 List<String> list = memberService.autocomplete();
		 String[] arr = new String[list.size()];
		 int size=0;
		 for (String tem : list) {
			 arr[size++]=tem;
		 }
		 mav.addObject("arr",arr);
		 mav.setViewName("jsonView");
		 return mav;
	 }
	 
	 //===================================== 관리자
	 
	  @RequestMapping(value="/admin/adminBoard",method=RequestMethod.GET)
	  public ModelAndView adminBoard() {
		  List<MemberDTO> list = memberService.getMember();
		  	
		  ModelAndView mav = new ModelAndView();
		  mav.addObject("list", list);
		  mav.setViewName("/admin/adminBoard");
		  
		  return mav;
	  }
	 
	  @RequestMapping(value="/all/memberDelete",method=RequestMethod.POST)
	  public String memberDelete(@RequestParam String username) {
			
		  System.out.println(username);
		  
			memberService.memberDelete(username);
			
			
			return "redirect:/all/adminBoard";
	  }
	
	 
	 

}

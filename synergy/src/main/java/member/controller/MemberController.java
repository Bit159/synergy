package member.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cardBoard.bean.CardBoardDTO;
import cardBoard.service.CardBoardService;
import member.bean.MemberDTO;
import member.service.MemberService;

@Controller
public class MemberController {
	@Autowired(required=false)
	private MemberService memberService;
	@Autowired 
	private CardBoardService cardBoardService;

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
//	@RequestMapping(value="/member/welcome",method=RequestMethod.GET)
//	public String welcome() {
//		return "/member/welcome";
//	}
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
	 @GetMapping(value="/all/welcome")
	 public String welcome() {
		 return "/all/welcome";
	 }
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
	 @PostMapping(value="/member/regist")
	 public String regist(@ModelAttribute CardBoardDTO groupDTO) {
		 Map<String, Object> map = new HashedMap<String, Object>();
		 map.put("title", groupDTO.getTitle());
		 System.out.println(groupDTO.getTopic());
		 System.out.println(groupDTO.getLocation());
		 System.out.println(groupDTO.getPeople());
		 System.out.println(groupDTO.getContent());
		 cardBoardService.regist(groupDTO);
		 return "/member/card";
	 }
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
}

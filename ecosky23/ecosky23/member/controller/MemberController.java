package ecosky23.member.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ecosky23.member.bean.MatchDTO;
import ecosky23.member.bean.MemberDTO;
import ecosky23.member.bean.ProgrammingDTO;
import ecosky23.member.bean.Search;
import ecosky23.member.bean.TotalDTO;
import ecosky23.member.service.MemberService;
import net.sf.json.JSONArray;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberDTO memberDTO;
	@Autowired
	private TotalDTO totalDTO;
	@Autowired
	private ProgrammingDTO programmingDTO;
	@Autowired
	private MatchDTO matchDTO;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@RequestMapping(value="/all/loginForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String loginForm() {
		return "/all/loginForm";
	}
	
	@RequestMapping(value="/all/signUpForm",method=RequestMethod.POST)
	public String signUpForm() {
		return "/all/signUpForm";
	}
	
	
	@RequestMapping(value="/member/welcome",method=RequestMethod.GET)
	public String welcome() {
		return "/member/welcome";
	}
	@ResponseBody
	@RequestMapping(value="/member/myPage",method=RequestMethod.POST)
	public ModelAndView myPage(@RequestParam String username, HttpServletRequest request, HttpSession session){
		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		
		
		memberDTO = memberService.getMyPage(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDTO", memberDTO);
		mav.setViewName("/member/myPage");
		
		return mav;
	}
	
	
	@RequestMapping(value="/admin/admin_index",method=RequestMethod.GET)
	public String admin_index() {
		return "/admin/admin_index";
	}
	
	@RequestMapping(value="/all/joinForm",method=RequestMethod.GET)
	public String joinForm() {
		return "/all/joinForm";
	}
	
	@RequestMapping(value="/all/join",method=RequestMethod.POST)
	public String join(@RequestParam Map<String, String> map) {
		
		memberService.join(map);
		return "redirect:/all/loginForm";
	}
	
	
	  @ResponseBody
	  @RequestMapping(value="/member/withdrawal",method=RequestMethod.POST) 
	  public String withdrawal(@RequestParam String username, String password) {
	  
	  List<MemberDTO> list = memberService.getWithdrawalList(username);
		  
	  
	  
	  System.out.println(memberDTO.getPassword());//db에서 받아온 비밀번호
	  
	  System.out.println(username);
	  System.out.println(password);//입력한 비밀번호 
	  
	  String passwordEqual = null;
	  
	 //encoder.matches(password, memberDTO.getPassword());
	 
	 if(true==encoder.matches(password, memberDTO.getPassword())){
		 
		 System.out.println("비번 맞춤");
		 
		 memberService.withdrawal(username);
		 
		 passwordEqual = "equal";
	 
	 }else {
	
		 System.out.println("비번 틀림");
		  
		 passwordEqual = "unEqual";
	 }
	  
	 return passwordEqual;
	 
	 }
	  
	  @ResponseBody
	  @RequestMapping(value="/member/revise",method=RequestMethod.POST) 
	  public String revise(@RequestParam String username, String password, String nickname) {
		  
	  System.out.println(username);
	  System.out.println(password);//입력한 password
	  System.out.println(nickname);//입력한 닉네임
	  
	  		String revice = null;
	  
		 if(password == null || password == ""){ 
			 
			 Map<String, String> map = new HashMap<String, String>();
		 	 map.put("username", username);
		 	 map.put("nickname", nickname);
		 	 
		 	 List<MemberDTO> list = memberService.getNickName(nickname);
		 	 
		 	 
		 	 String dbNickName = "";
		 	 
		 	 for(int i = 0; i<list.size(); i++) {
		 		dbNickName = list.get(i).getNickname();
		 	 }
		 	 
		 	 if(dbNickName==null || dbNickName== "") {
		 		 
		 		System.out.println("닉네임 수정 가능");
		 		 
		 		memberService.nicknameRevice(map);
		 		
		 		revice = "onlyNickname";
		 		
		 	 }else {
		 	
		 		 System.out.println("닉네임 수정 불가능");
		 		 
		 		revice = "fail";
		 		 
		 	 }

 
		 }else{
	  
			  Map<String, String> map = new HashMap<String, String>();
			  map.put("username", username);
			  map.put("password", password);
			  map.put("nickname", nickname);
			  
			  List<MemberDTO> list = memberService.getNickName(nickname);
			  
			  String dbNickName = "";
			 	 
			  for(int i = 0; i<list.size(); i++) {
				dbNickName = list.get(i).getNickname();
			  }
			  
			  if(dbNickName.equals(nickname)) {
				  
				  memberService.passwordRevise(map);
				  System.out.println("비밀번호 바꿀때 닉네임이 같음");
				  
				  revice ="onlyPassword";
				  
			  }else {
				  
	  
				  System.out.println("비밀번호 바꿀때 닉네임이 다름");
				  memberService.revise(map);
				  
				  revice ="dualSuccess";
			  }
			  
			  
			  
			  
			 
		 }
		 
		 return revice;
	 
	  }
	  
		
	  
	  @RequestMapping(value="/all/adminBoard",method=RequestMethod.GET) 
	  public String adminBoard(Model model 
			  					, @RequestParam(required = false, defaultValue = "1") int page
			  					, @RequestParam(required = false, defaultValue = "1") int range
			  					, @RequestParam(required = false, defaultValue = "username") String searchType
			  					, @RequestParam(required = false) String keyword
			  					, @ModelAttribute("search") Search search) {
		  
		  	
		  	model.addAttribute("search", search);	
			search.setSearchType(searchType);
			search.setKeyword(keyword);
		  
		  
		  //전체 게시글의 수
		  int listCnt = memberService.getBoardListCnt(search);
		  
		  //Pagination 객체생성
		   search.pageInfo(page, range, listCnt);

		  
		  
		  List<MemberDTO> list = memberService.getBoardList(search);
		  
		  model.addAttribute("list", list);
		  model.addAttribute("pagination", search);
		  
		  
		  return "/all/adminBoard";
		  
	  }
	  
	  
	  @RequestMapping(value="/all/adminStats",method=RequestMethod.GET) 
	  public String adminStats() {
		  
		  
			/*
			 * List<TotalDTO> list = memberService.getTotalStats();
			 * 
			 * System.out.println(list);
			 * 
			 * 
			 * 
			 * model.addAttribute("list", list);
			 */
		  
		  return "/all/adminStats";
		  
	  }
	  
	  @ResponseBody
	  @RequestMapping(value="/all/getAdminStats",method=RequestMethod.POST) 
	  public JSONArray getAdminStats() {
		  
		  
		List<TotalDTO> list =  memberService.getTotalStats();
		  
		
		JSONArray json = new JSONArray();
		json.addAll(list);
		
		return json;
		  
	  }
	  
	  
	  @RequestMapping(value="/all/programmingStats",method=RequestMethod.GET) 
	  public String programmingStats() {
		  
		  
		  return "/all/programmingStats";
		  
	  }
	  
	  @ResponseBody
	  @RequestMapping(value="/all/getProgrammingStats",method=RequestMethod.POST) 
	  public JSONArray getProgrammingStats() {
		  
		  
		List<ProgrammingDTO> list =  memberService.getProgrammingStats();
		  
		
		JSONArray json = new JSONArray();
		json.addAll(list);
		
		return json;
		  
	  }
	  
	  @RequestMapping(value="/all/admin_map",method=RequestMethod.GET) 
	  public String admin_map() {
		  
		  return "/all/admin_map";
		  
	  }
	  
	  @ResponseBody
	  @RequestMapping(value="/all/admin_map_getList",method=RequestMethod.POST) 
	  public JSONArray admin_map_getList() {
		  
		  List<MatchDTO> list =  memberService.getListFromMatch();
		  
		  JSONArray json = new JSONArray();
		  json.addAll(list);
		  
		  return json;
		  
	  }  
	  
	  
	 
	  @RequestMapping(value="/all/memberDelete",method=RequestMethod.POST)
	  public String memberDelete(@RequestParam String username) {
			
		  System.out.println(username);
		  
			memberService.memberDelete(username);
			
			
			return "redirect:/all/adminBoard";
	  }
	
}

/*
 * @RequestMapping(value="/member/logout",method=RequestMethod.GET) public
 * String logout(HttpSession session) { session.invalidate();
 * 
 * return "/member/loginForm"; }
 */
/*
 * @ResponseBody
 * 
 * @RequestMapping(value="/member/login",method=RequestMethod.POST) public
 * String login(@RequestParam String id, @RequestParam String pwd, HttpSession
 * session) {
 * 
 * MemberDTO memberDTO = memberService.login(id, pwd); String result = "";
 * 
 * if(memberDTO != null) { result = "success"; session.setAttribute("memId",
 * memberDTO.getId());
 * 
 * }else { result = "fail"; }
 * 
 * return result; }
 */

/*
	  @RequestMapping(value="/all/getSearchAdminBoard",method=RequestMethod.GET) 
	  public String getSearchAdminBoard(@RequestParam(value="nowpage", defaultValue = "0") int nowpage, Model model, 
			  							String username, String nickname) {
		  
		  int row =3;
		  
		  List<MemberDTO> list = new ArrayList<>(); 
		  
		  Map<String, String> map = new HashMap<String, String>();
		  map.put("username", username);
		  map.put("nickname", nickname);
		  List<MemberDTO> temp = memberService.getSearchAdminBoard(map); 
		  
		  int totalpage = temp.size() / 3; if((list.size() % 3) > 0) totalpage++; 
		   
		  for(int i = nowpage * row; i < (nowpage * row) + row; i++) { 
			  list.add(temp.get(i)); 
		  }
		  
		  model.addAttribute("nowpage", nowpage); 
		  model.addAttribute("totalpage", totalpage); 
		  model.addAttribute("list", list);

		  return "/all/adminBoard";
		  
	  }
	  
 */
package member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import member.bean.ChattingDTO;
import member.bean.ChattingRoomDTO;
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
	
	//============================================ 메인페이지
	
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
	
	
	//=========================================== 로그인
	
	@GetMapping("/all/loginForm")
	public String loginForm(Model model) {
		OAuth2Operations oauth2Operations = googleConnectionFactory.getOAuthOperations();
		String url = oauth2Operations.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameter);
		model.addAttribute("google_url", url);

		return "/all/loginForm";
	}
	
	@PostMapping("/socialLogin")
	@ResponseBody
	public void socialLogin(@RequestParam String username1) throws Exception{
		HttpPost httpPost = new HttpPost("http://localhost:8080/login");
		//model.addAttribute("username", username1);
		//model.addAttribute("password", "bitcamp159");
		
		System.out.println(username1);
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("username", username1));
		param.add(new BasicNameValuePair("password", "bitcamp159"));
		
		httpPost.setEntity(new UrlEncodedFormEntity(param));
		
		HttpResponse response = httpClient.execute(httpPost);

		HttpEntity entity =  response.getEntity();
		String content = EntityUtils.toString(entity);
		System.out.println(content + 1);
		
		//return "/all/socialLogin";
	}

	//==========================================구글 로그인 콜백메소드
	@RequestMapping(value="/googleLogin", method= {RequestMethod.GET, RequestMethod.POST})
	public String googleCallBack(@RequestParam String code, RedirectAttributes attr) {
		//여기서 이메일 값을 받아올 수 없어서 따로 처리를 한다.
		attr.addAttribute("password", "bitcamp159");
		System.out.println("콜백메서드");
		
		/*
		 * BufferedReader in = null; InputStream is = null; InputStreamReader isr =
		 * null; JSONParser jsonParser = new JSONParser();
		 * 
		 * String userId = null;
		 * 
		 * 
		 * System.out.println(code); String idToken = code.split("-")[1];
		 * 
		 * String url = "https://oauth2.googleapis.com/tokeninfo"; url += "?id_token=" +
		 * idToken;
		 * 
		 * try {
		 * 
		 * URL gurl = new URL(url); HttpURLConnection conn =
		 * (HttpURLConnection)gurl.openConnection();
		 * 
		 * is = conn.getInputStream(); isr = new InputStreamReader(is, "UTF-8"); in =
		 * new BufferedReader(isr);
		 * 
		 * JSONObject jsonObj = (JSONObject)jsonParser.parse(in);
		 * 
		 * userId= jsonObj.get("sub").toString(); String name =
		 * jsonObj.get("name").toString(); String email =
		 * jsonObj.get("email").toString(); String imageUrl =
		 * jsonObj.get("picture").toString();
		 * 
		 * System.out.println(userId); System.out.println(name);
		 * System.out.println(email); System.out.println(imageUrl);
		 * 
		 * 
		 * } catch (MalformedURLException e) { e.printStackTrace();
		 * 
		 * } catch (IOException e1) { e1.printStackTrace();
		 * 
		 * } catch (ParseException e2) { e2.printStackTrace(); }
		 */
		
		return "redirect:/all/loginForm";

	}
	//=========================================== 회원가입
	
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
		
		return "redirect:/all/welcome";
	}
	
	@PostMapping("/all/checkMember")
	@ResponseBody
	public String checkMember(@RequestParam String username, ServletRequest req) {
		MemberDTO memberDTO = memberService.checkMember(username);
		HttpServletRequest request = (HttpServletRequest)req;
		
		System.out.println(username);
		if(memberDTO != null) {
			/*Collection<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
			
			authList.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
			User principal = new User(username, "bitcamp159", true, true, true, true, authList);
			HttpSession session = request.getSession();
			SecurityContext context = SecurityContextHolder.getContext();
			
			
			session.setAttribute("SPRING_SECURITY_CONTEXT", principal);*/
			
			return "ok";
			
		}else {
			return "none";
		}
		
	}
	
	//====================================== 마이페이지
	
	@ResponseBody
	@RequestMapping(value="/member/myPage",method=RequestMethod.POST)
	public ModelAndView myPage(@RequestParam String username){
		MemberDTO memberDTO = memberService.checkMember(username);
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDTO", memberDTO);
		mav.setViewName("/member/myPage");
		
		return mav;
	}
	
	@RequestMapping(value="/member/revise",method=RequestMethod.POST) 
	public String revise(@RequestParam String username, String password, String nickname) {
		if(password == null || password == ""){	 
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

	//================================== 게시판
	
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
		memberService.memberDelete(username);
			
			
		return "redirect:/all/adminBoard";
	}
	  
	//============================================================== 채팅방
		
	@GetMapping("/member/chatting")
	public String chatting() {
		return "/member/chatting";
	}

	/*
	 * @PostMapping("/member/sendMessage")
	 * 
	 * @ResponseBody public void sendMessage(@RequestParam String
	 * text, @RequestParam String chattingRoom) { String sender =
	 * text.split(",")[1]; String message = text.split(",")[0];
	 * memberService.sendMessage(sender, message, chattingRoom);
	 * 
	 * }
	 */
	  
	@GetMapping("/member/chattingList")
	public String chattingList() {
		return "/member/chattingList";
	}
	
	@PostMapping("/member/getChattingRoom")
	@ResponseBody
	public ModelAndView getChattingRoom(@RequestParam String username, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<ChattingRoomDTO> list = memberService.getChattingRoom(username);
		
		session.setAttribute("chattingCheck", "create");	
		System.out.println(session.getAttribute("SPRING_SECURITY_CONTEXT"));
		
		for(ChattingRoomDTO dto : list) {
			ChattingDTO chattingDTO = memberService.getLastChatting(dto.getChattingRoom());
			dto.setChat(chattingDTO.getChat());
			dto.setChat_date(chattingDTO.getChat_date());
			dto.setNickname(chattingDTO.getNickname());
			dto.setUsername(username);
		}
		
		mav.addObject("list", list);
		mav.setViewName("jsonView");
		
		return mav;
	}
	@GetMapping("/member/getAllChatting")
	@ResponseBody
	public ModelAndView getAllChatting() {
		ModelAndView mav = new ModelAndView();
		ChattingRoomDTO chattingRoomDTO = memberService.getAllChatting();
		mav.addObject("chattingRoomDTO", chattingRoomDTO);
		mav.setViewName("jsonView");
		
		return mav;
	}
	  
	@PostMapping("/member/getChatting")
	@ResponseBody
	public ModelAndView getChatting(@RequestParam String chattingRoom) {
		ModelAndView mav = new ModelAndView();
		System.out.println(chattingRoom);
		List<ChattingDTO> list = memberService.getChatting(chattingRoom);
		mav.addObject("list", list);
		mav.setViewName("jsonView");
		  
		return mav;
	}
	  
	@GetMapping("/member/createChat")
	@ResponseBody
	public void createChat() {
		memberService.createChat();
	}
	
}

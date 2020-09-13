package byungjooYang.member.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
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

import byungjooYang.member.bean.ChattingDTO;
import byungjooYang.member.bean.ChattingRoomDTO;
import byungjooYang.member.service.ChatService;

@Controller
public class ByungjooYangController{
	
	@Autowired
	private ChatService chatService;
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	@Autowired
	private OAuth2Parameters googleOAuth2Parameter;
	
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
		List<ChattingRoomDTO> list = chatService.getChattingRoom(username);
		
		session.setAttribute("chattingCheck", "create");	
		System.out.println(session.getAttribute("SPRING_SECURITY_CONTEXT"));
		
		for(ChattingRoomDTO dto : list) {
			ChattingDTO chattingDTO = chatService.getLastChatting(dto.getChattingRoom());
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
		ChattingRoomDTO chattingRoomDTO = chatService.getAllChatting();
		mav.addObject("chattingRoomDTO", chattingRoomDTO);
		mav.setViewName("jsonView");
		return mav;
	}
	  
	@PostMapping("/member/getChatting")
	@ResponseBody
	public ModelAndView getChatting(@RequestParam String chattingRoom) {
		ModelAndView mav = new ModelAndView();
		System.out.println(chattingRoom);
		List<ChattingDTO> list = chatService.getChatting(chattingRoom);
		mav.addObject("list", list);
		mav.setViewName("jsonView");
		return mav;
	}
	  
	@GetMapping("/member/createChat")
	@ResponseBody
	public void createChat() {
		chatService.createChat();
	}
	
}

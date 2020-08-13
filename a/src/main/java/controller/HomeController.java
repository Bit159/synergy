package controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;
import user.MatchDTO;
import user.UserDAO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private UserDAO userDAO;
	
	@GetMapping("/")	public String home(Locale locale, Model model) { return "all//index"; }
	@GetMapping("/info") public String info() { return "all/info"; }
	@GetMapping("/map") public String map() { return "member/map";	}
	@GetMapping("/board") public String board() { return "member/board"; }
	@GetMapping("/join") public String signup() { return "all/join"; }
	@GetMapping("/accessError") public String accessDenied() { return "all/accessDenied"; }
	@GetMapping("/insert_match") public ModelAndView insert_match() {
			ModelAndView mav = new ModelAndView();
			List<MatchDTO> list = userDAO.getListFromMatch();
			mav.addObject("list", list);
			mav.setViewName("all/insert_match");
			return mav; 
		}
	
	@GetMapping("/mylogin") public void loginInput(String error, String logout, Model model) {
		logger.info("error: " + error);
		logger.info("logout: " + logout);
		
		if(error != null) model.addAttribute("error", "Login Error Occured! Check Your Account");
		if(logout != null) model.addAttribute("logout", "Logout!");
	}
	
	@GetMapping("/mylogout")
	public void logoutGET() {logger.info("custom logout");}

	@PostMapping(path="/insert_match_done", produces="application/json;charset=UTF-8")
	public @ResponseBody JSONObject insertMatch(@RequestBody JSONObject json, @Autowired MatchDTO matchDTO) {
		matchDTO.setEmail("jpcnani@naver.com");
		matchDTO.setX(json.getDouble("x"));
		matchDTO.setY(json.getDouble("y"));
		matchDTO.setRange(json.getDouble("range"));
		matchDTO.setTime(json.getString("time"));
		matchDTO.setTopic(json.getString("topic"));
		matchDTO.setCareer(json.getInt("career"));
		matchDTO.setPeople(json.getInt("people"));
		int result = userDAO.insertMatch(matchDTO);
		
		JSONObject rjson = (result==1) ? json : null;
		
		return rjson;
	}
	
	
	@PostMapping(path="/delete_match", produces="application/json;charset=UTF-8")
	public @ResponseBody JSONObject deleteMatch(@RequestBody JSONObject json, @Autowired MatchDTO matchDTO) {
		matchDTO.setEmail("jpcnani@naver.com");
		matchDTO.setX(json.getDouble("x"));
		matchDTO.setY(json.getDouble("y"));
		matchDTO.setRange(json.getDouble("range"));
		matchDTO.setTime(json.getString("time"));
		matchDTO.setTopic(json.getString("topic"));
		matchDTO.setCareer(json.getInt("career"));
		matchDTO.setPeople(json.getInt("people"));
		System.out.println(json.getString("time"));
		int result = userDAO.deleteMatch(matchDTO);
		JSONObject rjson = (result==1) ? json : null;
		
		return rjson;
	}
	
	
	
}

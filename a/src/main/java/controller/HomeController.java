package controller;

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
	
	@GetMapping("/all/customLogin") public void loginInput(String error, String logout, Model model) {
		logger.info("error: " + error);
		logger.info("logout: " + logout);
		
		if(error != null) model.addAttribute("error", "Login Error Occured! Check Your Account");
		if(logout != null) model.addAttribute("logout", "Logout!");
	}

	@PostMapping(path="/insertMatch", produces="application/json;charset=UTF-8")
	public @ResponseBody String insertMatch(@RequestBody JSONObject json, @Autowired MatchDTO matchDTO) {
		matchDTO.setEmail("jpcnani@naver.com");
		matchDTO.setX(json.getDouble("x"));
		matchDTO.setY(json.getDouble("y"));
		matchDTO.setRange(json.getDouble("range"));
		int result = userDAO.insertMatch(matchDTO);
		return result+"개 성공";
	}
	
}

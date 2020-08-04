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
	
	@GetMapping(path = "/")
	public String home(Locale locale, Model model) {
		return "/WEB-INF/views/index.jsp";
	}
	
	@GetMapping(path = "/login")
	public String login() {
		return "/WEB-INF/views/login.jsp";
	}
	@GetMapping(path = "/join")
	public String signup() {
		return "/WEB-INF/views/join.jsp";
	}
	
	@GetMapping(path="/map")
	public String map() {
		return "/WEB-INF/views/map.jsp";
	}
	
	@PostMapping(path="/insertMatch", produces="application/json;charset=UTF-8")
	public @ResponseBody String insertMatch(@RequestBody JSONObject json, @Autowired MatchDTO matchDTO) {
		matchDTO.setX(json.getDouble("x"));
		matchDTO.setY(json.getDouble("y"));
		matchDTO.setRange(json.getDouble("range"));
		int result = userDAO.insertMatch(matchDTO);
		return result+"개 성공";
	}
	
}

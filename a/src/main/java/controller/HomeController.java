package controller;

import java.text.DateFormat;
import java.util.Date;
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

import user.MatchDTO;
import user.UserDAO;
import user.UserDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	@Autowired
	private UserDAO userDAO;
	
	@GetMapping(path = "/")
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		List<UserDTO> list = userDAO.selectAll();
		return "/WEB-INF/views/home.jsp";
	}
	
	@GetMapping(path="/map")
	public String map() {
		return "/WEB-INF/views/map.jsp";
	}
	
	@PostMapping(path="/insertMatch", produces="application/x-www-form-urlencoded;charset=UTF-8")
	public String insertMatch(@RequestBody MatchDTO matchDTO) {
		logger.info(matchDTO.toString());
		logger.info(userDAO.insertMatch(matchDTO)+"");
		return "/WEB-INF/views/home.jsp";
	}
	
	
	@GetMapping(path = "/login")
	public String login(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "/WEB-INF/views/login.jsp";
	}
	
}

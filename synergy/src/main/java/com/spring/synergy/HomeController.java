package com.spring.synergy;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping(path = "/")
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "/main/index.jsp";
	}
	
	@GetMapping(path = "/login")
	public String login(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "/main/login.jsp";
	}
	

}

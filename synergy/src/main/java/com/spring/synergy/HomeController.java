package com.spring.synergy;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import board.BoardService;
import board.CBoardDTO;
import user.UserDAO;
import user.UserDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private UserDAO userDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		/*
		List<UserDTO> list = userDAO.selectAll();
		for(UserDTO dto: list) System.out.println(dto.toString());
		List<CBoardDTO> list2 = userDAO.getCBoardList();
		for(CBoardDTO d : list2) System.out.println(d.toString());
		*/
		
		return "home";
	}
	
	/* 게시판 크롤링 관련 */
	/*
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board/cboardList")
	public ModelAndView cboardList() {
		List<CBoardDTO> list = boardService.getCBoardList();
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("/board/cboardList");
		return mav;
	}
	*/
	
}

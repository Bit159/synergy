package board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.bean.CBoardDTO;
import board.dao.BoardDAO;
import board.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardDAO boardDAO;
	
	@GetMapping("/all/boardList")
	public ModelAndView boardList() {
		List<CBoardDTO> list = boardService.getCBoardList();
		System.out.println(list.get(0).getContent());
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);		
		mav.setViewName("/all/boardList");
		return mav;
	}
	
	@GetMapping("/board/boardList/{bno}")
	public ModelAndView boardView(@PathVariable("bno") int bno) {
		CBoardDTO cBoardDTO = boardService.getBoard(bno);
		System.out.println(cBoardDTO.getTitle());
		ModelAndView mav = new ModelAndView();
		mav.addObject("cBoardDTO", cBoardDTO);
		mav.setViewName("/board/boardView");
		return mav;
	}
	
	@RequestMapping(value = "/board/boardList1", method = RequestMethod.GET)
	public ModelAndView boardList1() {
		
		/*
		 * List<CBoardDTO> list = userDAO.getCBoardList();
		 * System.out.println(list.get(0).getContent());
		 */
		List<CBoardDTO> list = boardDAO.getCBoardList();
		System.out.println(list.get(0).getContent());
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);		
		mav.setViewName("/board/boardList1");
		return mav;
	}
	
	@GetMapping("/board/home")
	public String home() {
		return "/board/home";
	}
	
	@PostMapping(path="/board/getBoardList")
	@ResponseBody
	public ModelAndView getBoardList(@RequestParam String pg) {
		List<BoardDTO> list = boardService.getBoardList(pg);
		
		//페이징 처리
		BoardPaging boardPaging = boardService.boardPaging(pg);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", pg);
		mav.addObject("list", list);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@GetMapping("/board/boardView")
	public ModelAndView boardView() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
}
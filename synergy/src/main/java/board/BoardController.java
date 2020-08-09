package board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/board/boardList", method = RequestMethod.GET)
	public String boardList(@RequestParam(required = false, defaultValue = "1") String pg) {
		return "/board/boardList";
	}
	
	@RequestMapping(value = "/board/home", method = RequestMethod.GET)
	public String home() {
		return "/board/home";
	}
	
	@RequestMapping(value = "/board/getBoardList", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getBoardList(@RequestParam String pg, HttpSession session) {
		String memId = (String) session.getAttribute("memId");
		
		List<BoardDTO> list = boardService.getBoardList(pg);
		
		//페이징 처리
		BoardPaging boardPaging = boardService.boardPaging(pg);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", pg);
		mav.addObject("list", list);
		mav.addObject("memId", memId);
		mav.setViewName("jsonView");
		return mav;
	}
}

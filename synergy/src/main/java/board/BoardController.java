package board;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import user.UserDAO;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private BoardDAO boardDAO;
	
	@GetMapping("/board/boardList")
	public ModelAndView boardList() {
		List<CBoardDTO> list = boardService.getCBoardList();
		System.out.println(list.get(0).getContent());
		Date now = new Date();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("now", now);
		mav.setViewName("/board/boardList");
		return mav;
	}
	
	@GetMapping("/board/{bno}")
	public ModelAndView boardView(@PathVariable("bno") int bno) {
		CBoardDTO cBoardDTO = boardService.getCBoard(bno);
		List<CBoardDTO> list = boardService.getCBoardList();
		System.out.println(cBoardDTO.getTitle());
		List<CBoardReplyDTO> replyList = boardService.getCBoardReplyList(bno);
		System.out.println(replyList);
		boardService.hitUpdate(bno);
		
		Date now = new Date();
		ModelAndView mav = new ModelAndView();
		mav.addObject("cBoardDTO", cBoardDTO);
		mav.addObject("list", list);
		mav.addObject("now", now);
		mav.addObject("replyList", replyList);
		mav.setViewName("/board/boardView");
		return mav;
	}
	
	@RequestMapping(value = "/board/boardList1", method = RequestMethod.GET)
	public ModelAndView boardList1() {
		List<CBoardDTO> list = boardDAO.getCBoardList();
		System.out.println(list.get(0).getContent());
		Date now = new Date();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("now", now);
		mav.setViewName("/board/boardList1");
		return mav;
	}
	
	@GetMapping("/board/home")
	public String home() {
		return "/board/home";
	}
	
	@PostMapping(path="/board/getBoardList")
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
	@GetMapping("/board/boardView")
	public ModelAndView boardView() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	@GetMapping(path="/board/boardReply")
	public ModelAndView boardReply(@RequestParam String reply, int bno, HttpSession session) {
		System.out.println("reply:"+reply+" bno:"+bno);
		/* String nickname = (String) session.getAttribute("nickname"); */
		String nickname = "nickname";
		System.out.println("nickname:"+nickname);
		List<CBoardReplyDTO> replyList = boardService.getCBoardReplyList(bno);
		System.out.println("replyList:"+replyList);
		Date now = new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno",bno);
		map.put("reply", reply);
		map.put("nickname", nickname);
		map.put("now", now);
		boardService.boardReply(map);
		ModelAndView mav = new ModelAndView();
		mav.addObject("replyList", replyList);
		mav.setViewName("jsonView");
		return mav;
	}
	
	
}
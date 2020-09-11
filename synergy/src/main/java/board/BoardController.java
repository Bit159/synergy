package board;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import oracle.jdbc.proxy.annotation.Post;
import user.UserDAO;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardDAO boardDAO;
	
	// 크롤링 보드 리스트
	@GetMapping("/board/boardList")
	public ModelAndView boardList(@RequestParam(required=false, defaultValue = "1") int pg
							   	 ,@RequestParam(required=false, defaultValue = "1") int range
							   	 , @RequestParam(required = false, defaultValue = "title") String searchType
								 , @RequestParam(required = false) String keyword
								 , @ModelAttribute("search") Search search) throws Exception  {
		
		// 검색
		/* Search search = new Search(); */
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		// 페이지
		int page =  pg;
		System.out.println("페이지"+page+"범위"+range);
		
		// 검색, 페이징 적용된 전체 게시글 수
		int listCnt = boardService.getCBoardListCnt(search); 
		
		// 검색
		search.pageInfo(page, range, listCnt);
		
		// 페이지네이션
		Pagination paging = new Pagination();
		paging.pageInfo(page, range, listCnt); 
		System.out.println("paging: "+paging);
		
		System.out.println(search.getKeyword());
		System.out.println(search.getSearchType());
		
		// 검색, 페이징 적용된 보드리스트
		List<CBoardDTO> list = boardService.getCBoardList(search);
		
		// 작성시간 표시 위한 현재 Date 객체
		Date now = new Date();
		
		ModelAndView mav = new ModelAndView();
		// 검색
		mav.addObject("search",search);
		// 페이징(검색 적용)
		mav.addObject("paging",search);
		mav.addObject("list", list);
		mav.addObject("now", now);
		mav.setViewName("/board/boardList");
		return mav;
	}
	
	//자유게시판 리스트
	@GetMapping("/bboard/boardList2")
	public ModelAndView boardList2(@RequestParam(required=false, defaultValue = "1") int pg
							   	 ,@RequestParam(required=false, defaultValue = "1") int range
							   	 , @RequestParam(required = false, defaultValue = "title") String searchType
								 , @RequestParam(required = false) String keyword
								 , @ModelAttribute("search") Search search) throws Exception  {
		
		// 검색
		/* Search search = new Search(); */
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		// 페이지
		int page =  pg;
		System.out.println("페이지"+page+"범위"+range);
		
		// 검색, 페이징 적용된 전체 게시글 수
		int listCnt = boardService.getBBoardListCnt(search); 
		
		// 검색
		search.pageInfo(page, range, listCnt);
		
		// 페이지네이션
		Pagination paging = new Pagination();
		paging.pageInfo(page, range, listCnt); 
		System.out.println("paging: "+paging);
		
		// 검색, 페이징 적용된 보드리스트
		List<BBoardDTO> list = boardService.getBBoardList(search); 
		
		// 작성시간 표시 위한 현재 Date 객체
		Date now = new Date();
		
		ModelAndView mav = new ModelAndView();
		// 검색
		mav.addObject("search",search);
		// 페이징(검색 적용)
		mav.addObject("paging",search);
		mav.addObject("list", list);
		mav.addObject("now", now);
		mav.setViewName("/board/boardList2");
		return mav;
	}
	
	//크롤링 보드뷰
	@GetMapping("/board/{bno}")
	public ModelAndView boardView(@PathVariable("bno") int bno,
								  @RequestParam(required=false, defaultValue = "1") int pg
								 ,@RequestParam(required=false, defaultValue = "1") int range
								 , @RequestParam(required = false, defaultValue = "title") String searchType
								 , @RequestParam(required = false) String keyword) throws Exception {
		
		// 검색
		Search search = new Search();
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		// 페이지
		int page =  pg;
		System.out.println("페이지"+page+"범위"+range);
		
		// 검색, 페이징 적용된 전체 게시글 수
		int listCnt = boardService.getCBoardListCnt(search); 
		
		// 검색
		search.pageInfo(page, range, listCnt);
		
		// 페이지네이션
		Pagination paging = new Pagination();
		paging.pageInfo(page, range, listCnt); 
		System.out.println("paging: "+paging);	
		
		// 원글 불러오기
		CBoardDTO cBoardDTO = boardService.getCBoard(bno);
		
		// 보드뷰 하단부 검색, 페이징 적용된 보드리스트
		List<CBoardDTO> list = boardService.getCBoardList(search);
		System.out.println(cBoardDTO.getTitle());
		
		// 보드뷰 해당 리플 리스트
		List<CBoardReplyDTO> replyList = boardService.getCBoardReplyList(bno);
		System.out.println(replyList);
		
		// 조회수 1증가
		boardService.hitUpdate(bno);
		
		// 작성시간 표시 위한 현재 Date 객체
		Date now = new Date();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("paging",search);
		mav.addObject("cBoardDTO", cBoardDTO);
		mav.addObject("list", list);
		mav.addObject("now", now);
		mav.addObject("replyList", replyList);
		mav.setViewName("/board/boardView");
		return mav;
	}
	
	//자유게시판 보드뷰
		@GetMapping("/bboard/{bno}")
		public ModelAndView bBoardView(@PathVariable("bno") int bno,
									  @RequestParam(required=false, defaultValue = "1") int pg
									 ,@RequestParam(required=false, defaultValue = "1") int range
									 , @RequestParam(required = false, defaultValue = "title") String searchType
									 , @RequestParam(required = false) String keyword) throws Exception {
			
			// 검색
			Search search = new Search();
			search.setSearchType(searchType);
			search.setKeyword(keyword);
			
			// 페이지
			int page =  pg;
			System.out.println("페이지"+page+"범위"+range);
			
			// 검색, 페이징 적용된 전체 게시글 수
			int listCnt = boardService.getBBoardListCnt(search); 
			
			// 검색
			search.pageInfo(page, range, listCnt);
			
			// 페이지네이션
			Pagination paging = new Pagination();
			paging.pageInfo(page, range, listCnt); 
			System.out.println("paging: "+paging);	
			
			// 원글 불러오기
			BBoardDTO bBoardDTO = boardService.getBBoard(bno);
			
			// 보드뷰 하단부 검색, 페이징 적용된 보드리스트
			List<BBoardDTO> list = boardService.getBBoardList(search);
			System.out.println(bBoardDTO.getTitle());
			
			// 보드뷰 해당 리플 리스트
			List<BBoardReplyDTO> replyList = boardService.getBBoardReplyList(bno);
			System.out.println(replyList);
			
			// 조회수 1증가
			boardService.boardHitUpdate(bno);
			
			// 작성시간 표시 위한 현재 Date 객체
			Date now = new Date();
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("paging",search);
			mav.addObject("bBoardDTO", bBoardDTO);
			mav.addObject("list", list);
			mav.addObject("now", now);
			mav.addObject("replyList", replyList);
			mav.setViewName("/board/boardView2");
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
	
	/*
	//삭제 예정
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
	*/
	@GetMapping("/board/boardView")
	public ModelAndView boardView() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	//크롤링 보드 댓글 생성
	@PostMapping(path="/board/boardReply")
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
		boardService.replyUpdate(bno);
		ModelAndView mav = new ModelAndView();
		mav.addObject("replyList", replyList);
		mav.setViewName("jsonView");
		return mav;
	}
	
	//자유게시판 댓글 생성
		@PostMapping(path="/bboard/boardReply2")
		public ModelAndView bboardReply(@RequestParam String reply, int bno, HttpSession session) {
			System.out.println("reply:"+reply+" bno:"+bno);
			/* String nickname = (String) session.getAttribute("nickname"); */
			String nickname = "nickname";
			System.out.println("nickname:"+nickname);
			List<BBoardReplyDTO> replyList = boardService.getBBoardReplyList(bno);
			System.out.println("replyList:"+replyList);
			Date now = new Date();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bno",bno);
			map.put("reply", reply);
			map.put("nickname", nickname);
			map.put("now", now);
			boardService.boardReply2(map);
			boardService.replyUpdate2(bno);
			ModelAndView mav = new ModelAndView();
			mav.addObject("replyList", replyList);
			mav.setViewName("jsonView");
			return mav;
		}
	
	//크롤링 보드 댓글 삭제
	@PostMapping("/board/replyDelete")
	public ModelAndView replyDelete(@RequestParam int rno, int bno, HttpSession session) {
		System.out.println("rno="+rno);
		/* String nickname = (String) session.getAttribute("nickname"); */
		boardService.replyDelete(rno);
		System.out.println("bno="+bno);
		boardService.replyDeleteUpdate(bno);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/boardView");
		return mav;
	}
	
	//자유게시판 댓글 삭제
		@PostMapping("/bboard/replyDelete2")
		public ModelAndView replyDelete2(@RequestParam int rno, int bno, HttpSession session) {
			System.out.println("rno="+rno);
			/* String nickname = (String) session.getAttribute("nickname"); */
			boardService.replyDelete2(rno);
			System.out.println("bno="+bno);
			boardService.replyDeleteUpdate2(bno);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("board/boardView");
			return mav;
		}
	
	@PostMapping("/board/replyWrite") 
	public ModelAndView replyWrite(@RequestParam String reply_writer_text, int bno, HttpSession session) {
		System.out.println(reply_writer_text);
		System.out.println(bno);
		String text = reply_writer_text;
		String nickname = "nickname";
		Date now = new Date();
		
		Map map = new HashMap();
		map.put("text",text);
		map.put("bno",bno);
		map.put("nickname", nickname);
		map.put("now", now);
		boardService.replyWrite(map);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/boardView");
		return mav;
	}
	
	// 크롤링 보드 댓글 수정
	@PostMapping("/board/replyModify")
	public ModelAndView replyModify(@RequestParam String reply, int rno, HttpSession session) {
		System.out.println(reply);
		System.out.println(rno);
		/* String nickname = (String) session.getAttribute("nickname"); */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rno",rno);
		map.put("reply", reply);
		boardService.replyModify(map);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/board/boardView");
		return mav;
	}
	
	// 자유게시판 댓글 수정
		@PostMapping("/bboard/replyModify2")
		public ModelAndView replyModify2(@RequestParam String reply, int rno, HttpSession session) {
			System.out.println(reply);
			System.out.println(rno);
			/* String nickname = (String) session.getAttribute("nickname"); */
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rno",rno);
			map.put("reply", reply);
			boardService.replyModify2(map);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/board/boardView");
			return mav;
		}
	
	
	// 자유게시판 작성 폼	
	@GetMapping("/board/boardWriteForm")
	public ModelAndView boardWriteForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/board/boardWriteForm");
		return mav;
	}
	
	// 자유게시판 수정 폼	
		@GetMapping("/board/boardModifyForm")
		public ModelAndView bboardWriteForm() {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/board/boardModifyForm");
			return mav;
		}
	
	// 자유게시판 보드 작성
	@PostMapping("/board/boardWrite")
	public ModelAndView boardWrite(@RequestParam String title, String content, String nickname) {
		Date now = new Date();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title",title);
		map.put("content",content);
		map.put("nickname",nickname);
		map.put("now", now);
		boardService.writeBBoard(map); 
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/board/boardList2");
		return mav;
	}
	
	// 자유게시판 보드 삭제
	@PostMapping("/board/boardDelete")
	public ModelAndView boardDelete(@RequestParam int bno) {
		boardService.deleteBBoard(bno);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/board/boardList2");
		return mav;
	}
	
}
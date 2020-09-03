package member.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cardBoard.bean.CardBoardDTO;
import cardBoard.bean.CardBoardPaging;
import cardBoard.bean.CardBoardReplyDTO;
import cardBoard.service.CardBoardService;
import member.service.MemberService;
import net.sf.json.JSONArray;

@Controller
public class MemberController {
	@Autowired(required=false)
	private MemberService memberService;
	@Autowired 
	private CardBoardService cardBoardService;

	@RequestMapping(value="/all/loginForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String loginForm() {
		return "/all/loginForm";
	}
//	@RequestMapping(value="/member/login",method=RequestMethod.POST)
//	public void login(@RequestParam Map<String, String> map, HttpSession session) {
//		memberService.login(map);
//		String result = "";
//		if(memberDTO != null) {
//			return "/member/welcome";
//		}else {
//			return "/member/loginForm";
//		}
//	}
//	@RequestMapping(value="/member/welcome",method=RequestMethod.GET)
//	public String welcome() {
//		return "/member/welcome";
//	}
//	@RequestMapping(value="/member/logout",method=RequestMethod.GET)
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "/member/loginForm";
//	}
	@RequestMapping(value="/member/signUpForm",method=RequestMethod.GET)
	public String signUpForm() {
		return "/member/signUpForm";
	}
	@GetMapping(value="/all/joinForm")
	public String joinForm() {
		return "/all/joinForm";
	}
	 @PostMapping(value="/all/join")
	 @ResponseBody
	 public void join(@RequestParam Map<String, String> map) {
		 memberService.join(map);
	 }
	 @GetMapping(value="/admin/adminPage")
	 public String adminPage() {
		 return "/admin/adminPage";
	 }
	 @GetMapping(value="/all/welcome")
	 public String welcome() {
		 return "/all/welcome";
	 }
	 
//	 =============카드게시판 관련================
	 @GetMapping(value="/member/createGroup")
	 public String createGroup() {
		 return "/member/createGroup";
	 }
	 //모집글 등록
	 @PostMapping(value="/member/regist")
	 public String regist(@ModelAttribute CardBoardDTO cardBoardDTO,Principal principal) {
		 String username = principal.getName();
		 String nickname = memberService.getNickname(username);
		 cardBoardDTO.setNickname(nickname);
		 System.out.println(cardBoardDTO.getSeq());
		 cardBoardService.regist(cardBoardDTO);
		 return "redirect:/member/cardBoardList";
	 }
	 //지역 자동완성
	 @GetMapping(value="/member/autocomplete")
	 @ResponseBody
	 public ModelAndView autocomplete() {
		 ModelAndView mav = new ModelAndView();
		 List<String> list = memberService.autocomplete();
		 String[] arr = new String[list.size()];
		 int size=0;
		 for (String tem : list) {
			 arr[size++]=tem;
		 }
		 mav.addObject("arr",arr);
		 mav.setViewName("jsonView");
		 return mav;
	 }
	 //게시판 카드리스트 뿌리기
	 @GetMapping(value="/member/cardBoardList")
	 public ModelAndView cardBoardList(
			 @RequestParam(required=false, defaultValue = "1") int pg
			,@RequestParam(required=false, defaultValue = "1") int range
	 		,@RequestParam(required=false) String[] locations,
	 		 @RequestParam(required=false) String topic){

		 System.out.println("주제 :" + topic);
		 
		 CardBoardPaging paging = new CardBoardPaging();
		 int page =  pg; 
		 int listCnt = cardBoardService.getBoardListCnt(); //전체갯수
		 paging.pageInfo(page, range, listCnt);
		 
		 Map<String,Object> map = new HashedMap<String,Object>();
		 ModelAndView mav = new ModelAndView();
		 if(locations==null && topic==null) {
			 //전체리스트
			 List<CardBoardDTO> listAll = cardBoardService.getCardBoardList(paging);
			 mav.addObject("list",listAll);
			 mav.setViewName("/member/cardBoard");
		 } else if(locations==null && topic!=null) {
			 List<CardBoardDTO> listNoloc = cardBoardService.searchCardNoloc(topic);
			 mav.addObject("list",listNoloc);
			 mav.setViewName("/member/cardBoard");
		 } else if(locations!=null && topic!=null) {
			 for (int i = 0; i < locations.length; i++) {
				 map.put("locations["+i+"]", locations[i]);
			 }
			 map.put("topic",topic);
			 List<Object> list2 = new ArrayList<Object>(map.values());
			 //지역 및 주제 검색
			 List<CardBoardDTO> list = cardBoardService.searchCard(list2);
			 mav.addObject("list",list);
		 }
		 
		 mav.addObject("paging",paging);
		 return mav;
	 }
	 //지역 선택창
	 @GetMapping(path="/member/getLocation",produces="application/json;charset=UTF-8")
	 @ResponseBody
	 public JSONArray getLocation() {
		 List<List<String>> list = new ArrayList<List<String>>();
		 List<String> _list = new ArrayList<String>();
		 _list.add("서울");
		 _list.add("경기");
		 _list.add("인천");
		 _list.add("부산");
		 _list.add("대구");
		 _list.add("광주");
		 _list.add("대전");
		 _list.add("울산");
		 _list.add("세종");
		 _list.add("강원");
		 _list.add("경남");
		 _list.add("경북");
		 _list.add("전남");
		 _list.add("전북");
		 _list.add("충남");
		 _list.add("충북");
		 _list.add("제주");
		 for(int i = 0; i < _list.size(); i++) {
		  list.add(cardBoardService.getLocationList(_list.get(i)));
		 }
		 JSONArray json = new JSONArray();
		 json.addAll(list);
		 return json;
	 }
	 //조건설정 후 검색
	 @GetMapping(value="/member/searchCard")
	 public ModelAndView searchCard(@RequestParam(required=false) String[] locations,@RequestParam String topic){
		 JSONArray json = new JSONArray();
		 ModelAndView mav = new ModelAndView();
		 List<CardBoardDTO> listAll = cardBoardService.searchCardNoloc(topic);
		 if(locations==null) {
//			 json.addAll(listAll);
			 mav.addObject("listAll",listAll);
		 }
		 Map<String,Object> map = new HashedMap<String,Object>();
		 for (int i = 0; i < locations.length; i++) {
			 map.put("locations["+i+"]", locations[i]);
		 }
		 map.put("topic",topic);
		 
		 List<Object> list2 = new ArrayList<Object>(map.values());
		 List<CardBoardDTO> list = cardBoardService.searchCard(list2);
		 if(locations!=null) {
//			 json.addAll(list);
			 mav.addObject("listAll",list);
		 }
		 mav.setViewName("/member/cardBoard");
		 return mav;
	 }
	 //댓글 보기
	 @GetMapping(value="/member/cardBoardView")
	 public ModelAndView cardBoardView(@RequestParam int seq,Principal principal) {
		 CardBoardDTO dto = cardBoardService.getCardContent(seq);
		 List<CardBoardReplyDTO> replyList= cardBoardService.getReplyList(seq);
		 String username = principal.getName();
		 String nickname = memberService.getNickname(username);
		 ModelAndView mav = new ModelAndView();
		 mav.addObject("dto",dto);
		 mav.addObject("replyList",replyList);
		 mav.addObject("nickname",nickname);
		 mav.setViewName("/member/cardBoardView");
		 return mav;
	 }
	 //댓글등록
	 @GetMapping(value="/member/writeReply")
	 public String writeReply(@RequestParam String reply,@RequestParam int seq,Principal principal) {
		 String username = principal.getName();
		 String nickname = memberService.getNickname(username);
		 CardBoardReplyDTO dto = new CardBoardReplyDTO();
		 dto.setSeq(seq);
		 dto.setReply(reply);
		 dto.setNickname(nickname);
		 CardBoardDTO cardDTO = new CardBoardDTO();
		 cardDTO.setSeq(seq);
		 cardBoardService.writeReply(dto);
		 cardBoardService.replyCntup(cardDTO);
		 return "/member/cardBoardView";
	 }
	 //댓글 삭제
	 @GetMapping(value="/member/deleteReply")
	 public String deleteReply(@RequestParam int rseq, @RequestParam int seq){
		 cardBoardService.deleteReply(rseq);
		 CardBoardDTO cardDTO = new CardBoardDTO();
		 cardDTO.setSeq(seq);
		 cardBoardService.replyCntdown(cardDTO);
		 return "/member/cardBoardView";
	 }
	 //댓글 수정
	 @GetMapping(value="/member/modifyReply")
	 public String modifyReply(@RequestParam int rseq,@RequestParam String reply){
		 CardBoardReplyDTO dto = new CardBoardReplyDTO();
		 dto.setRseq(rseq);
		 dto.setReply(reply);
		 cardBoardService.modifyReply(dto);
		 return "/member/cardBoardView";
	 }
}

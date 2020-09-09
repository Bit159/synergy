package controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import notifier.NotDTO;
import user.CBoardDTO;
import user.MatchDTO;
import user.UserDAO;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private UserDAO userDAO;
	
	@ResponseBody
	@PostMapping(path="/createSchedule", produces="application/json;charset=UTF-8")
	public JSONObject createSchedule(@RequestBody JSONObject json, @Autowired NotDTO dto) {
		System.out.println(json);
		dto.setUsername(json.getString("username"));
		dto.setGroup(json.getInt("group"));
		dto.setStart(new Date(json.getLong("start")));
		dto.setEnd(new Date(json.getLong("end")));
		dto.setPlace(json.getString("place"));
		dto.setTitle(json.getString("title"));
		dto.setContent(json.getString("content"));
		int result = userDAO.createSchedule(dto);
		if (result != 1) logger.info("일정 생성중 에러가 발생하였습니다.");
		json.put("no", userDAO.getGreatestNo());
		return json;
	}
	
	@PostMapping("/removeSchedule")
	@ResponseBody
	public int removeSchedule(@RequestBody int no) {
		return userDAO.removeSchedule(no);
	}
	@PostMapping("/updateSchedule")
	@ResponseBody
	public int updateSchedule(@RequestBody JSONObject json) {
		NotDTO dto = new NotDTO();
		System.out.println(json);
		return 0;
	}
	
	@GetMapping("/schedules")
	public String schedules() {
		return "/all/schedules";
	}
	@PostMapping(path="/getSchedules", produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONArray getMySchedules() {
		JSONArray json = new JSONArray();
		json.addAll(userDAO.getSchedules());
		return json;
	}
	
	@GetMapping("/info") public String info() { return "/WEB-INF/views/all/info"; }
	@GetMapping("/map") public String map() { return "/WEB-INF/views/member/map";	}
	@GetMapping("/board") public ModelAndView board() { 
		ModelAndView mav = new ModelAndView();
		List<CBoardDTO> list = userDAO.getBoardList();
		mav.addObject("list", list);
		mav.setViewName("/WEB-INF/views/all/boardList");
		return mav; 
		
	}
	@GetMapping("/join") public String signup() { return "/WEB-INF/views/all/join"; }
	@GetMapping("/accessError") public String accessDenied() { return "/WEB-INF/views/all/accessDenied"; }
	@GetMapping("/insert_match") public ModelAndView insert_match() {
		ModelAndView mav = new ModelAndView();
		List<MatchDTO> list = userDAO.getListFromMatch();
		mav.addObject("list", list);
		mav.setViewName("/WEB-INF/views/all/insert_match");
		return mav; 
	}
	@GetMapping("/_insert_match") public ModelAndView _insert_match() {
		ModelAndView mav = new ModelAndView();
		List<MatchDTO> list = userDAO.getListFromMatch();
		mav.addObject("list", list);
		mav.setViewName("/member/_insert_match");
		return mav; 
	}
	
	@GetMapping("/mylogin") public void loginInput(String error, String logout, Model model) {
		logger.info("error: " + error);
		logger.info("logout: " + logout);
		if(error != null) model.addAttribute("error", "Login Error Occured! Check Your Account");
		if(logout != null) model.addAttribute("logout", "Logout!");
	}
	
	@GetMapping("/mylogout")
	public void logoutGET() {logger.info("custom logout");}

	//매칭 위시 넣기
	@PostMapping(path="/insert_match_done", produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONObject insertMatch(@RequestBody JSONObject json, @Autowired MatchDTO matchDTO) {
		matchDTO.setUsername("jpcnani@naver.com");
		matchDTO.setMycareer(userDAO.getMycareer(matchDTO.getUsername()));
		matchDTO.setX(json.getDouble("x"));
		matchDTO.setY(json.getDouble("y"));
		matchDTO.setRange(json.getDouble("range"));
		matchDTO.setTime(json.getString("time"));
		matchDTO.setTopic(json.getString("topic"));
		matchDTO.setCareer(json.getInt("career"));
		matchDTO.setPeople(json.getInt("people"));
		int result = userDAO.insertMatch(matchDTO);
		JSONObject return_json = (result == 1) ? json : null;
		return return_json;
	}
	
	
	//매칭 위시 삭제
	@PostMapping(path="/delete_match", produces="application/json;charset=UTF-8")
	public @ResponseBody JSONObject deleteMatch(@RequestBody JSONObject json, @Autowired MatchDTO matchDTO) {
		System.out.println(json);
		matchDTO.setUsername("jpcnani@naver.com");
		matchDTO.setX(json.getDouble("x"));
		matchDTO.setY(json.getDouble("y"));
		matchDTO.setRange(json.getDouble("range"));
		matchDTO.setCareer(json.getInt("career"));
		matchDTO.setPeople(json.getInt("people"));
		int result = userDAO.deleteMatch(matchDTO);
		JSONObject rjson = (result==1) ? json : null;
		return rjson;
	}
	
	
	// admin map
	@GetMapping("/admin_map")
	public String admin_map() {return "/WEB-INF/views//all/admin_map";	}
	
	
	
	@PostMapping(path="/admin_map_getList", produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONArray admin_map_getList() {
		JSONArray json= new JSONArray();
		json.addAll(userDAO.getListFromMatch());
		return json;
	}
	//-----------
	
	
	
	@GetMapping("/")public String home() {
		return "/all/welcome"; 
	}
	
	
	
}

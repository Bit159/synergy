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
	public int createSchedule(@RequestBody JSONObject json, @Autowired NotDTO dto) {
		dto.setUsername(json.getString("username"));
		dto.setTime(new Date(json.getLong("time")));
		dto.setPlace(json.getString("place"));
		dto.setTitle(json.getString("title"));
		dto.setContent(json.getString("content"));
		dto.setCreated(new Date(json.getLong("created")));
		dto.setUpdated(new Date(json.getLong("updated")));
		int result = userDAO.createSchedule(dto);
		if (result != 1) logger.info("일정 생성중 에러가 발생하였습니다.");
		return userDAO.getGreatestNo();
	}
	
	@PostMapping("/deleteSchedule")
	@ResponseBody
	public int deleteSchedule(@RequestBody int no) {
		return userDAO.deleteSchedule(no);
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
		matchDTO.setTime1(json.getString("time1"));
		try{matchDTO.setTime2(json.getString("time2"));
		}catch(JSONException e) {matchDTO.setTime2(null);}
		try{matchDTO.setTime3(json.getString("time3"));
		}catch(JSONException e) {matchDTO.setTime3(null);}
		matchDTO.setTopic1(json.getString("topic1"));
		try{matchDTO.setTopic2(json.getString("topic2"));
		}catch(JSONException e) {matchDTO.setTopic2(null);}
		try{matchDTO.setTopic3(json.getString("topic3"));
		}catch(JSONException e) {matchDTO.setTopic3(null);}
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
	
	
	
	@GetMapping("/")public String home(Locale locale, Model model) {
		List<MatchDTO> originalList = userDAO.getListFromMatch();
		System.out.println("originalList : " + originalList);
		peopleFilter(originalList);
		
		return "/all/welcome"; 
	}
	
	public List<MatchDTO> peopleFilter(List<MatchDTO> originalList) {
		List<MatchDTO> result = new ArrayList<>();
		
		for (int i = 0; i < originalList.size(); i++) {
			for (int j = 0; j < originalList.size(); j++) {
				//인원 기준이 0(무관)이 아닌 경우
				if(originalList.get(i).getPeople() != 0) {
					//현재 기준과 인원 기준이 같은 녀석들만 리스트에 담는다.
					if(originalList.get(i).getPeople() == originalList.get(j).getPeople()) {
						
					}
				//인원 기준이 0(무관)인 경우
				}else {
					
				}
			}
		}
		
		return result; 
	}
	
	
	
	public List<MatchDTO> timeFilter(List<MatchDTO> originalList) {
		List<MatchDTO> result = new ArrayList<>();
		for (int i = 0; i < originalList.size(); i++) {
			String time = originalList.get(i).getTime1();
			for (int j = 0; j < originalList.size(); j++) {
				MatchDTO dto = originalList.get(j);
				if(time.equals(dto.getTime1()) || time.equals(dto.getTime2()) || time.equals(dto.getTime3())) {
					//기준의 인원수와 일치해야함.
					//기준이 0(무관)이면 리스트에 담긴 모든 녀석들과 
					
					
				}
			}
		}
		//people 규칙
		//0 : 무관
		//3 : 3명
		//4 : 4~6명
		//7 : 7~9명
		//10 : 10명 이상
		System.out.println("result : " +result);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

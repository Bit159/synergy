package member.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.WebSocketSession;

@RequestMapping("/chat")
@Controller
public class ChatHandler{
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	private static Logger logger = LoggerFactory.getLogger(ChatHandler.class);

	@GetMapping
	public String chat() {
		
		return "/member/chat";
	}
	
	
	
	
	
	
	
	

}

package member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import member.bean.ChattingDTO;
import member.service.MemberService;

@Controller
public class StompSocketController {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	private MemberService memberService;
	
	public StompSocketController() {
		System.out.println("stomp 객체 생성");
	}
	
	@MessageMapping("/message")
	public void send(ChattingDTO chattingDTO) {
		memberService.sendMessage(chattingDTO);
		
		simpMessagingTemplate.convertAndSend("/topic/" + chattingDTO.getChattingRoom(), chattingDTO);
	}
}

package byungjooYang.member.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import byungjooYang.member.bean.ChattingDTO;
import byungjooYang.member.service.MemberService;

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
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		chattingDTO.setChat_date(format.format(date));
		System.out.println(format.format(date));
		
		memberService.sendMessage(chattingDTO);
		simpMessagingTemplate.convertAndSend("/topic/" + chattingDTO.getChattingRoom(), chattingDTO);
	}
}

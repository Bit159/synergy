package byungjooYang.member.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import byungjooYang.member.bean.ChattingDTO;
import byungjooYang.member.bean.ChattingRoomDTO;
import byungjooYang.member.bean.MemberDTO;
import byungjooYang.member.dao.ChatDAO;
import ecosky23.member.bean.MatchDTO;
import ecosky23.member.bean.ProgrammingDTO;
import ecosky23.member.bean.Search;
import ecosky23.member.bean.TotalDTO;

@Service
public class ChatServiceImpl implements ChatService {
	
	@Autowired
	private ChatDAO chatDAO;

	@Override
	public void sendMessage(String sender, String message, String chattingRoom) {
		Map<String, String> map = new HashedMap<String, String>();
		map.put("sender", sender);
		map.put("message", message);
		map.put("chattingRoom", chattingRoom);
		chatDAO.sendMessage(map);
	}

	@Override
	public void sendMessage(ChattingDTO chattingDTO) {
		chatDAO.sendMessage(chattingDTO);

	}

	@Override
	public List<ChattingDTO> getChatting(String chattingRoom) {
		return chatDAO.getChatting(chattingRoom);
	}

	@Override
	public void createChat() {
		chatDAO.createChat();
	}

	@Override
	public List<ChattingRoomDTO> getChattingRoom(String username) {
		return chatDAO.getChattingRoom(username);
	}

	@Override
	public String getNickname(String username) {
		return chatDAO.getNickname(username);
	}

	@Override
	public ChattingDTO getLastChatting(String chattingRoom) {
		return chatDAO.getLastChatting(chattingRoom);
	}

	@Override
	public ChattingRoomDTO getAllChatting() {
		return chatDAO.getAllChatting();
	}

}

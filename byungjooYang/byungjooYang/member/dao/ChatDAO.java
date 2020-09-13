package byungjooYang.member.dao;

import java.util.List;
import java.util.Map;

import byungjooYang.member.bean.ChattingDTO;
import byungjooYang.member.bean.ChattingRoomDTO;
import byungjooYang.member.bean.MemberDTO;

public interface ChatDAO {

	public void sendMessage(Map<String, String> map);
	public List<ChattingDTO> getChatting(String chattingRoom);
	public void createChat();
	public List<ChattingRoomDTO> getChattingRoom(String username);
	public void sendMessage(ChattingDTO chattingDTO);
	public String getNickname(String username);
	public ChattingDTO getLastChatting(String chattingRoom);
	public ChattingRoomDTO getAllChatting();

}

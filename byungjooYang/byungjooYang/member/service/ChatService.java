package byungjooYang.member.service;

import java.util.List;
import java.util.Map;

import byungjooYang.member.bean.ChattingDTO;
import byungjooYang.member.bean.ChattingRoomDTO;
import byungjooYang.member.bean.MemberDTO;
import ecosky23.member.bean.MatchDTO;
import ecosky23.member.bean.ProgrammingDTO;
import ecosky23.member.bean.Search;
import ecosky23.member.bean.TotalDTO;

public interface ChatService {

	public List<ChattingDTO> getChatting(String chattingRoom);
	public void createChat();
	public List<ChattingRoomDTO> getChattingRoom(String username);
	public void sendMessage(String sender, String message, String chattingRoom);
	public void sendMessage(ChattingDTO chattingDTO);
	public String getNickname(String username);
	public ChattingDTO getLastChatting(String chattingRoom);
	public ChattingRoomDTO getAllChatting();

}

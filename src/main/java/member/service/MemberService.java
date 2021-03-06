package member.service;

import java.util.List;
import java.util.Map;

import member.bean.ChattingDTO;
import member.bean.ChattingRoomDTO;
import member.bean.MemberDTO;

public interface MemberService {

	public MemberDTO login(Map<String, String> map);

	public void join(Map<String, String> map);

	public MemberDTO checkMember(String username);

	public void addInfo(MemberDTO memberDTO);

	public MemberDTO getMyPage(Map<String, String> map);

	public List<String> autocomplete();

	public void withdrawal(String username);

	public List<MemberDTO> getMember();

	public void memberDelete(String username);

	public void nicknameRevice(Map<String, String> map);

	public void revise(Map<String, String> map);

	public void sendMessage(String sender, String message);

	public List<ChattingDTO> getChatting();

	public void createChat();

	public List<ChattingRoomDTO> getChattingRoom(String username);

}

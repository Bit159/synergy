package member.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import member.bean.ChattingDTO;
import member.bean.ChattingRoomDTO;
import member.bean.MemberDTO;
import member.dao.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	//======================================================= 로그인
	
	@Override
	public MemberDTO login(Map<String, String> map) {
		return memberDAO.login(map);
	}
	
	//======================================================= 회원가입
	
	@Override
	public void join(Map<String, String> map) {
		String password = passwordEncoder.encode(map.get("password"));
		map.replace("password", password);
		memberDAO.join(map);
		
	}
	
	@Override
	public void addInfo(MemberDTO memberDTO) {
		String password = passwordEncoder.encode(memberDTO.getPassword());
		memberDTO.setPassword(passwordEncoder.encode(password));
		System.out.println(passwordEncoder.matches("bitcamp159", password));
		memberDAO.addInfo(memberDTO);
	} //dto 방식으로 하면 로그인이 안됨. 이유는 모르겠음


	@Override
	public MemberDTO checkMember(String username) {
		return memberDAO.checkMember(username);
	}

	//======================================================= 마이페이지
	
	@Override
	public MemberDTO getMyPage(Map<String, String> map) {
		return memberDAO.getMyPage(map);
	}
	
	@Override
	public void withdrawal(String username) {
		memberDAO.withdrawal(username);
	}

	@Override
	public List<MemberDTO> getMember() {
		return memberDAO.getMember();
	}

	@Override
	public void memberDelete(String username) {
		memberDAO.memberDelete(username);
	}
	
	@Override // 회원정보 수정
	public void revise(Map<String, String> map) {
		String password = passwordEncoder.encode(map.get("password"));
		map.replace("password", password);
		
		memberDAO.revise(map);
	}
	
	@Override //닉네임만 수정
	public void nicknameRevice(Map<String, String> map) {
		memberDAO.nicknameRevise(map);
	}
	
	//======================================================= 자동완성
	
	@Override
	public List<String> autocomplete() {
		return memberDAO.autocomplete();
	}
	
	//======================================================= 채팅방
	
	@Override
	public void sendMessage(String sender, String message, String chattingRoom) {
		Map<String,String> map = new HashedMap<String, String>();
		map.put("sender", sender);
		map.put("message", message);
		map.put("chattingRoom", chattingRoom);
		memberDAO.sendMessage(map);
	}

	@Override
	public void sendMessage(ChattingDTO chattingDTO) {
		memberDAO.sendMessage(chattingDTO);
		
	}
	
	@Override
	public List<ChattingDTO> getChatting(String chattingRoom) {
		return memberDAO.getChatting(chattingRoom);
	}

	@Override
	public void createChat() {
		memberDAO.createChat();
	}

	@Override
	public List<ChattingRoomDTO> getChattingRoom(String username) {
		return memberDAO.getChattingRoom(username);
	}

	@Override
	public String getNickname(String username) {
		return memberDAO.getNickname(username);
	}

	@Override
	public ChattingDTO getLastChatting(String chattingRoom) {
		return memberDAO.getLastChatting(chattingRoom);
	}

	@Override
	public ChattingRoomDTO getAllChatting() {
		return memberDAO.getAllChatting();
	}

	
	
}

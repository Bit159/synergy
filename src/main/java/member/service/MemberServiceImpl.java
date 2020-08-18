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

	@Override
	public MemberDTO login(Map<String, String> map) {
		return memberDAO.login(map);
	}
	
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

	@Override
	public List<String> autocomplete() {
		return memberDAO.autocomplete();
	}

	@Override
	public void nicknameRevice(Map<String, String> map) {
		memberDAO.nicknameRevise(map);
	}

	@Override
	public void revise(Map<String, String> map) {
		String password = passwordEncoder.encode(map.get("password"));
		map.replace("password", password);
		
		memberDAO.revise(map);
	}

	@Override
	public void sendMessage(String sender, String message) {
		Map<String,String> map = new HashedMap<String, String>();
		map.put("sender", sender);
		map.put("message", message);
		memberDAO.sendMessage(map);
	}

	@Override
	public List<ChattingDTO> getChatting() {
		return memberDAO.getChatting();
	}

	@Override
	public void createChat() {
		memberDAO.createChat();
	}

	@Override
	public List<ChattingRoomDTO> getChattingRoom(String username) {
		return memberDAO.getChattingRoom(username);
	}
	
	
}

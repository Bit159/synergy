package ecosky23.member.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ecosky23.member.bean.MatchDTO;
import ecosky23.member.bean.MemberDTO;
import ecosky23.member.bean.ProgrammingDTO;
import ecosky23.member.bean.Search;
import ecosky23.member.bean.TotalDTO;
import ecosky23.member.dao.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public MemberDTO login(String id, String pwd) {
		return memberDAO.login(id, pwd);
		
	}

	@Override
	public void join(Map<String, String> map) {
		String password = encoder.encode(map.get("password"));
		map.replace("password", password);
		map.put("auth", "ROLE_MEMBER");
		memberDAO.join(map);
		
	}

	@Override
	public void withdrawal(String username) {
		
		memberDAO.withdrawal(username);
		
	}

	@Override
	public MemberDTO getMyPage(Map<String, String> map) {
		
		return memberDAO.getMyPage(map);
	}

	@Override
	public void revise(Map<String, String> map) {
		
		String password = encoder.encode(map.get("password"));
		map.replace("password", password);
		
		memberDAO.revise(map);
		
	}

	@Override
	public void nicknameRevice(Map<String, String> map) {
		
		memberDAO.nicknameRevise(map);
		
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
	public List<MemberDTO> getBoardList() {
		
		return memberDAO.getBoardList();
	}

	

	@Override
	public List<TotalDTO> getTotalStats() {
		
		return memberDAO.getTotalStats();
	}

	@Override
	public List<ProgrammingDTO> getProgrammingStats() {
		
		return memberDAO.getProgrammingStats();
	}

	@Override
	public List<MatchDTO> getListFromMatch() {
		
		return memberDAO.getListFromMatch();
	}

	@Override
	public int getBoardListCnt(Search search) {
		
		return memberDAO.getBoardListCnt(search);
	}

	@Override
	public List<MemberDTO> getBoardList(Search search) {
		
		return memberDAO.getBoardList(search);
	}

	@Override
	public List<MemberDTO> getWithdrawalList(String username) {
		
		return memberDAO.getWithdrawalList(username);
	}

	@Override
	public List<MemberDTO> getNickName(String nickname) {
		
		return memberDAO.getNickName(nickname);
	}

	@Override
	public void passwordRevise(Map<String, String> map) {
		String password = encoder.encode(map.get("password"));
		map.replace("password", password);
		
		memberDAO.passwordRevise(map);
		
	}


	
	

}

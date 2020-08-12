package member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
	public MemberDTO checkMember(String username) {
		return memberDAO.checkMember(username);
	}

	@Override
	public void addInfo(MemberDTO memberDTO) {
		String password = passwordEncoder.encode(memberDTO.getPassword());
		memberDTO.setPassword(passwordEncoder.encode(password));
		System.out.println(passwordEncoder.matches("bitcamp159", password));
		memberDAO.addInfo(memberDTO);
	}
}

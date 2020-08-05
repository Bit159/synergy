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
	private BCryptPasswordEncoder encoder;

	@Override
	public MemberDTO login(Map<String, String> map) {
		return memberDAO.login(map);
	}
	
	@Override
	public void join(Map<String, String> map) {
		String password = encoder.encode(map.get("password"));
		map.replace("password", password);
		map.put("auth", "ROLE_MEMBER");
		memberDAO.join(map);
		
	}
}

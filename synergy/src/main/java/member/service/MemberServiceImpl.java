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
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	public MemberDTO login(Map<String, String> map) {
		return memberDAO.login(map);
	}
	@Override
	public void join(Map<String, String> map) {
		String pwd = bcrypt.encode(map.get("password"));
		map.replace("password", pwd);
		map.put("authority","ROLE_MEMBER");
		memberDAO.join(map);
	}

}

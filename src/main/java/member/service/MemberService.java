package member.service;

import java.util.Map;

import member.bean.MemberDTO;

public interface MemberService {

	public MemberDTO login(Map<String, String> map);

	public void join(Map<String, Object> map);

}

package member.service;

import java.util.List;
import java.util.Map;

import member.bean.MemberDTO;

public interface MemberService {
	public MemberDTO login(Map<String, String> map);
	public void join(Map<String, String> map);
	public List<String> autocomplete();

}

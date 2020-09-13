package kkh1595.member.service;

import java.util.List;
import java.util.Map;

import kkh1595.member.bean.MemberDTO;

public interface MemberService {
	public MemberDTO login(Map<String, String> map);
	public void join(Map<String, String> map);
	public List<String> autocomplete();
	public String getNickname(String username);

}

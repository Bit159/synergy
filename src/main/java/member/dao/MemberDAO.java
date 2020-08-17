package member.dao;

import java.util.List;
import java.util.Map;

import member.bean.MemberDTO;

public interface MemberDAO {

	public MemberDTO login(Map<String, String> map);

	public void join(Map<String, String> map);

	public MemberDTO checkMember(String username);

	public void addInfo(MemberDTO memberDTO);

	public MemberDTO getMyPage(Map<String, String> map);

	public List<String> autocomplete();

	public void withdrawal(String username);

	public List<MemberDTO> getMember();

	public void memberDelete(String username);

	public void nicknameRevise(Map<String, String> map);

	public void revise(Map<String, String> map);

}

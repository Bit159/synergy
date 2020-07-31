package member.dao;

import member.bean.MemberDTO;

public interface MemberDAO {
	public MemberDTO login(String id, String pwd);

}

package member.dao;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;

@Repository
@Transactional
public class MemberDAOMybatis implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;
	// 안만들어도 상관없지만 Warning이 발생함

	@Override
	public MemberDTO login(String id, String pwd) {
		Map<String, String> map = new HashedMap<String, String>();
		System.out.println("dao "+ id);
		map.put("id", id);
		map.put("pwd", pwd);
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.login",map);
		return memberDTO;
	}
}

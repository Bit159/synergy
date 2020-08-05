package member.dao;

import java.util.Map;

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
	public MemberDTO login(Map<String, String> map) {
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.login",map);
		return memberDTO;
	}

	@Override
	public void join(Map<String, String> map) {
		sqlSession.insert("memberSQL.join",map);
	}
}

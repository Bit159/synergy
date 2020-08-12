package member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;

@Transactional
@Repository
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberDTO login(Map<String, String> map) {
		return sqlSession.selectOne("memberSQL.login", map);
	}

	@Override
	public void join(Map<String, String> map) {
		sqlSession.insert("memberSQL.join", map);
		
	}

	@Override
	public MemberDTO checkMember(String username) {
		return sqlSession.selectOne("memberSQL.checkMember", username);
	}

	@Override
	public void addInfo(MemberDTO memberDTO) {
		sqlSession.insert("memberSQL.addInfo", memberDTO);
	}
}

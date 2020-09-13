package ecosky23.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ecosky23.member.bean.MatchDTO;
import ecosky23.member.bean.MemberDTO;
import ecosky23.member.bean.ProgrammingDTO;
import ecosky23.member.bean.Search;
import ecosky23.member.bean.TotalDTO;
@Repository
@Transactional
public class AdminServiceImpl implements AdminDAO {
	@Autowired
	private SqlSession sqlSession;
	
	
	// ------------------ 관리자 ------------------
	
	//관리자 - 회원 리스트 불러오기
	@Override
	public List<MemberDTO> getMember() {
		return sqlSession.selectList("memberSQL.getMember");
	}
	
	//관리자 - 회원탈퇴시키기
	@Override
	public void memberDelete(String username) {
		sqlSession.delete("memberSQL.memberDelete", username);
		
	}
	
	//------------------ 관리자 끝 -----------------------
	
	
	
	//======================================================= 마이페이지
	
	//마이페이지
	@Override
	public MemberDTO getMyPage(Map<String, String> map) {
		return sqlSession.selectOne("memberSQL.getMyPage", map);
	}
	
	//회원탈퇴
	@Override
	public void withdrawal(String username) {
		sqlSession.delete("memberSQL.withrawal", username);
	}
	
	//닉네임 수정
	@Override
	public void nicknameRevise(Map<String, String> map) {
		sqlSession.update("memberSQL.nicknameRevise", map);
	}

	//여러개 수정할 때
	@Override
	public void revise(Map<String, String> map) {
		sqlSession.update("memberSQL.revise", map);
	}
	
	@Override
	public List<MatchDTO> getListFromMatch() {
		return sqlSession.selectList("memberSQL.getListFromMatch");
	}

	@Override
	public int getBoardListCnt(Search search) {
		return sqlSession.selectOne("memberSQL.getBoardListCnt", search);
	}

	@Override
	public List<MemberDTO> getBoardList(Search search) {
		return sqlSession.selectList("memberSQL.getBoardList", search);
	}

	@Override
	public List<MemberDTO> getWithdrawalList(String username) {
		return sqlSession.selectList("memberSQL.getWithdrawalList", username);
	}

	@Override
	public List<MemberDTO> getNickName(String nickname) {
		return sqlSession.selectList("memberSQL.getNickName", nickname);
	}

	@Override
	public void passwordRevise(Map<String, String> map) {
		sqlSession.update("memberSQL.passwordRevise", map);
	}

	@Override
	public List<MemberDTO> getBoardList() {
		return sqlSession.selectList("memberSQL.getBoardList");
	}

	@Override
	public List<TotalDTO> getTotalStats() {
		return sqlSession.selectList("memberSQL.getTotalStats");
	}

	@Override
	public List<ProgrammingDTO> getProgrammingStats() {
		return sqlSession.selectList("memberSQL.getProgrammingStats");
	}

	@Override
	public MemberDTO login(String id, String pwd) {
		return null;
	}

	//회원가입
	@Override
	public void join(Map<String, String> map) {
		sqlSession.insert("memberSQL.join", map);
	}

}

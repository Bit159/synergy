package member.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.ChattingDTO;
import member.bean.ChattingRoomDTO;
import member.bean.MemberDTO;

@Transactional
@Repository
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;
	private int chatNum = 1;
	
	//======================================================= 로그인
	
	@Override
	public MemberDTO login(Map<String, String> map) {
		return sqlSession.selectOne("memberSQL.login", map);
	}

	//======================================================= 회원가입
	
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

	//======================================================= 마이페이지
	
	@Override
	public MemberDTO getMyPage(Map<String, String> map) {
		return sqlSession.selectOne("memberSQL.getMyPage", map);
	}
	
	@Override
	public void withdrawal(String username) {
		sqlSession.delete("memberSQL.withrawal");
		
	}
	
	@Override
	public void nicknameRevise(Map<String, String> map) {
		sqlSession.update("memberSQL.nicknameRevise", map);
		
	}

	@Override
	public void revise(Map<String, String> map) {
		sqlSession.update("memberSQL.revise", map);
		
	}
	
	//======================================================= 관리자 페이지
	
	@Override
	public List<MemberDTO> getMember() {
		return sqlSession.selectList("memberSQL.getMember");
		
	}
	
	@Override
	public void memberDelete(String username) {
		sqlSession.delete("memberSQL.memberDelete", username);
		
	}
	
	//======================================================= 자동완성
	
	@Override
	public List<String> autocomplete() {
		return sqlSession.selectList("memberSQL.autocomplete");
		
	}
	
	//======================================================= 채팅방
	
	@Override
	public void sendMessage(Map<String, String> map) {
		sqlSession.insert("memberSQL.sendMessage", map);
		
	}
	
	@Override
	public void sendMessage(ChattingDTO chattingDTO) {
		sqlSession.insert("memberSQL.sendMessage", chattingDTO);
		
	}

	@Override
	public List<ChattingDTO> getChatting(String chattingRoom) {
		return sqlSession.selectList("memberSQL.getChatting", chattingRoom);
		
	}

	@Override
	public void createChat() {
		Map<String, String> map = new HashedMap<String, String>();
		String create_table = "create table chattingRoom" + chatNum
							+ "(username varchar(100),"
							+ "chat varchar(500),"
							+ "chat_date date)";
		
		map.put("create_table", create_table);
		System.out.println(create_table);
		sqlSession.selectOne("memberSQL.createChat", map);
		chatNum++;
		
	}

	@Override
	public List<ChattingRoomDTO> getChattingRoom(String username) {
		return sqlSession.selectList("memberSQL.getChattingRoom", username);
		
	}

}

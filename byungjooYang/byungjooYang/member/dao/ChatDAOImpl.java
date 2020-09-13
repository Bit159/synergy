package byungjooYang.member.dao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import byungjooYang.member.bean.ChattingDTO;
import byungjooYang.member.bean.ChattingRoomDTO;
import byungjooYang.member.bean.MemberDTO;
import ecosky23.member.bean.ProgrammingDTO;
import ecosky23.member.bean.TotalDTO;

@Transactional
@Repository
public class ChatDAOImpl implements ChatDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
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
		String uuid = UUID.randomUUID().toString();
		String chattingRoomName = "chattingRoom" + uuid;
		System.out.println(uuid);
		
		String checkRoomName = sqlSession.selectOne("memberSQL.checkRoomName", chattingRoomName);
		
		if(checkRoomName == null) {
			createChat();
			return;
		}
		
		Map<String, String> map = new HashedMap<String, String>();
		String create_table = "create table " + chattingRoomName
							+ "(username varchar(100),"
							+ "nickname varchar(100),"
							+ "chat varchar(500),"
							+ "chat_date varchar(100)";
		
		map.put("create_table", create_table);
		System.out.println(create_table);
		sqlSession.selectOne("memberSQL.createChat", map);
		//chatNum++;
		//UUID(Universally Unique Identifier)는 소프트웨어 구축에 쓰이는 식별자 표준. 국제기구에서 표준으로 사용. 고유성을 완벽하게 보장할 수는 없지만 중복될 가능성이 거의 없다고 인정되기 때문에 많이 사용한다.
	}

	@Override
	public List<ChattingRoomDTO> getChattingRoom(String username) {
		return sqlSession.selectList("memberSQL.getChattingRoom", username);
		
	}

	@Override
	public String getNickname(String username) {
		return sqlSession.selectOne("memberSQL.getNickname",username);
	}

	@Override
	public ChattingDTO getLastChatting(String chattingRoom) {
		return sqlSession.selectOne("memberSQL.getLastChatting", chattingRoom);
	}

	@Override
	public ChattingRoomDTO getAllChatting() {
		return sqlSession.selectOne("memberSQL.getAllChatting");
	}

}

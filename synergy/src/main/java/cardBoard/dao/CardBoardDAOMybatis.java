package cardBoard.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cardBoard.bean.CardBoardDTO;
import member.bean.MemberDTO;

@Repository
@Transactional
public class CardBoardDAOMybatis implements CardBoardDAO {
	@Autowired
	private SqlSession sqlSession;
	// 안만들어도 상관없지만 Warning이 발생함

	@Override
	public void regist(CardBoardDTO groupDTO) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("title", groupDTO.getTitle());
		map.put("topic", groupDTO.getTopic());
		map.put("location", groupDTO.getLocation());
		map.put("people", groupDTO.getPeople());
		map.put("content", groupDTO.getContent());
		sqlSession.insert("cardBoardSQL.regist",map);
	}

	@Override
	public List<CardBoardDTO> getCardBoardList() {
		return sqlSession.selectList("cardBoardSQL.getCardBoardList");
	}
	@Override
	public List<String> getLocationList(String location) {

		return sqlSession.selectList("cardBoardSQL.getLocationList",location);
	}


}

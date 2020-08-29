package cardBoard.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cardBoard.bean.CardBoardDTO;
import cardBoard.bean.CardBoardPaging;
import cardBoard.bean.CardBoardReplyDTO;
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
		map.put("nickname",groupDTO.getNickname());
		map.put("topic", groupDTO.getTopic());
		map.put("location", groupDTO.getLocation());
		map.put("people", groupDTO.getPeople());
		map.put("content", groupDTO.getContent());
		sqlSession.insert("cardBoardSQL.regist",map);
	}

	@Override
	public List<CardBoardDTO> getCardBoardList(CardBoardPaging paging) {
		return sqlSession.selectList("cardBoardSQL.getCardBoardList",paging);
	}
	@Override
	public List<String> getLocationList(String location) {

		return sqlSession.selectList("cardBoardSQL.getLocationList",location);
	}

	@Override
	public List<CardBoardDTO> searchCard(List<Object> list) {
		return sqlSession.selectList("cardBoardSQL.searchCard",list);
	}

	@Override
	public List<CardBoardDTO> searchCardNoloc(String topic) {
		return sqlSession.selectList("cardBoardSQL.searchCardNoloc",topic);
	}

	@Override
	public CardBoardDTO getCardContent(int seq) {
		return sqlSession.selectOne("cardBoardSQL.getCardContent",seq);
	}

	@Override
	public int getBoardListCnt() {
		return sqlSession.selectOne("cardBoardSQL.getBoardListCnt");
	}

	@Override
	public List<CardBoardReplyDTO> getReplyList(int seq) {
		return sqlSession.selectList("cardBoardSQL.getReplyList",seq);
	}
}

package cardBoard.dao;

import java.util.Map;

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
		sqlSession.insert("cardBoardSQL.regist",groupDTO);
	}

}

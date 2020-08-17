package board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import board.bean.BoardDTO;
import board.bean.CBoardDTO;
import lombok.Setter;

@Repository("boardDAO")
@Transactional
@Setter
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<BoardDTO> getBoardList(Map<String, Integer> map) {
		return sqlSession.selectList("boardSQL.getBoardList", map);
	}

	@Override
	public int getBoardTotalA() {
		return sqlSession.selectOne("boardSQL.getBoardTotalA");
	}

	@Override
	public List<CBoardDTO> getBoardList1() {
		return sqlSession.selectList("boardSQL.getBoardList1");
	}

	@Override
	public List<CBoardDTO> getCBoardList() {
		return sqlSession.selectList("boardSQL.getCBoardList");
	}

	@Override
	public CBoardDTO getBoard(int bno) {
		return sqlSession.selectOne("boardSQL.getBoard", bno);
	}

}

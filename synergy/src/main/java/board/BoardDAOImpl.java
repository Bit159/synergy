package board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;

@Repository("boardDAO")
@Transactional
@Setter
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;
	
	private BoardDTO dto;

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
	public CBoardDTO getCBoard(int bno) {
		return sqlSession.selectOne("boardSQL.getCBoard", bno);
	}

	@Override
	public List<CBoardReplyDTO> getCBoardReplyList(int bno) {
		return sqlSession.selectList("boardSQL.getCBoardReplyList", bno);
	}

	@Override
	public void boardReply(Map<String, Object> map) {
		sqlSession.insert("boardSQL.boardReply", map);
	}

	@Override
	public void hitUpdate(int bno) {
		sqlSession.update("boardSQL.hitUpdate", bno);
	}

	@Override
	public void replyDelete(int rno) {
		System.out.println("---- rno : " + rno);
		sqlSession.delete("boardSQL.replyDelete", rno);
	}

	@Override
	public void replyWrite(Map map) {
		sqlSession.insert("boardSQL.replyWrite", map);
	}

	@Override
	public void replyModify(Map<String, Object> map) {
		sqlSession.update("boardSQL.replyModify", map);
	}

}

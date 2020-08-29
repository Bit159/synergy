package board;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired 
	private HttpSession session;
	 
	@Autowired 
	private BoardPaging boardPaging;
	 
	
	@Override
	public List<BoardDTO> getBoardList(String pg) {
		//1페이지당 5개씩
		int endNum = Integer.parseInt(pg)*5;
		int startNum = endNum-4;
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		return boardDAO.getBoardList(map);
	}

	@Override
	public BoardPaging boardPaging(String pg) {
		int totalA = boardDAO.getBoardTotalA();//총글수
		
		boardPaging.setCurrentPage(Integer.parseInt(pg));
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();
		return boardPaging;
	}

	@Override
	public List<CBoardDTO> getBoardList1() {
		return boardDAO.getBoardList1();
	}

	@Override
	public List<CBoardDTO> getCBoardList() {
		List<CBoardDTO> list = boardDAO.getCBoardList();
		
		return list;
	}

	@Override
	public CBoardDTO getCBoard(int bno) {
		return boardDAO.getCBoard(bno);
	}

	@Override
	public List<CBoardReplyDTO> getCBoardReplyList(int bno) {
		return boardDAO.getCBoardReplyList(bno);
	}

	@Override
	public void boardReply(Map<String, Object> map) {
		boardDAO.boardReply(map);
	}

	@Override
	public void hitUpdate(int bno) {
		boardDAO.hitUpdate(bno);
	}

	@Override
	public void replyDelete(int rno) {
		boardDAO.replyDelete(rno);
		
	}

	@Override
	public void replyWrite(Map map) {
		boardDAO.replyWrite(map);
		
	}

	@Override
	public void replyModify(Map<String, Object> map) {
		System.out.println(map);
		boardDAO.replyModify(map);
	}

	@Override
	public void replyUpdate(int bno) {
		boardDAO.replyUpdate(bno);
	}

	@Override
	public void replyDeleteUpdate(int bno) {
		boardDAO.replyDeleteUpdate(bno);
	}

	@Override
	public int getBoardListCnt() {
		return boardDAO.getBoardListCnt();
	}

	@Override
	public List<CBoardDTO> getCBoardList(Pagination paging) {
		List<CBoardDTO> list = boardDAO.getCBoardList(paging);
		
		return list;
	}

}

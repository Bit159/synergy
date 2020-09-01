package board;

import java.util.List;
import java.util.Map;

public interface BoardService {

	public List<BoardDTO> getBoardList(String pg);

	public BoardPaging boardPaging(String pg);

	public List<CBoardDTO> getBoardList1();

	public List<CBoardDTO> getCBoardList();

	public CBoardDTO getCBoard(int bno);

	public List<CBoardReplyDTO> getCBoardReplyList(int bno);

	public void boardReply(Map<String, Object> map);

	public void hitUpdate(int bno);

	public void replyDelete(int rno);

	public void replyWrite(Map map);

	public void replyModify(Map<String, Object> map);

	public void replyUpdate(int bno);

	public void replyDeleteUpdate(int bno);

	public int getBoardListCnt();

	public List<CBoardDTO> getCBoardList(Pagination paging); 
	
	public List<CBoardDTO> getCBoardList(Search search);

	public int getBoardListCnt(Search search);

}

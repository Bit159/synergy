package board;

import java.util.List;
import java.util.Map;


public interface BoardDAO {

	public List<BoardDTO> getBoardList(Map<String, Integer> map);

	public int getBoardTotalA();

	public List<CBoardDTO> getBoardList1();

	public List<CBoardDTO> getCBoardList();

	public CBoardDTO getCBoard(int bno);

	public List<CBoardReplyDTO> getCBoardReplyList(int bno);

	public void boardReply(Map<String, Object> map);

	public void hitUpdate(int bno);

	public void replyDelete(int rno);

	public void replyWrite(String text);
	
	
}

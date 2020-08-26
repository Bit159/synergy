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

	public void replyWrite(String text);

}

package board;

import java.util.List;

public interface BoardService {

	public List<BoardDTO> getBoardList(String pg);

	public BoardPaging boardPaging(String pg);

	public List<CBoardDTO> getBoardList1();

	public List<CBoardDTO> getCBoardList();

}

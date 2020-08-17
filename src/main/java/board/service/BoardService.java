package board.service;

import java.util.List;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.bean.CBoardDTO;

public interface BoardService {

	public List<BoardDTO> getBoardList(String pg);

	public BoardPaging boardPaging(String pg);

	public List<CBoardDTO> getBoardList1();

	public List<CBoardDTO> getCBoardList();

	public CBoardDTO getBoard(int bno);

}

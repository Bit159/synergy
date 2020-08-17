package board.dao;

import java.util.List;
import java.util.Map;

import board.bean.BoardDTO;
import board.bean.CBoardDTO;


public interface BoardDAO {

	public List<BoardDTO> getBoardList(Map<String, Integer> map);

	public int getBoardTotalA();

	public List<CBoardDTO> getBoardList1();

	public List<CBoardDTO> getCBoardList();

	public CBoardDTO getBoard(int bno);
	
	
}

package board;

import java.util.List;
import java.util.Map;


public interface BoardDAO {

	public List<BoardDTO> getBoardList(Map<String, Integer> map);

	public int getBoardTotalA();
	
	
}

package cardBoard.dao;

import java.util.List;
import java.util.Map;

import cardBoard.bean.CardBoardDTO;
import member.bean.MemberDTO;

public interface CardBoardDAO {
	public void regist(CardBoardDTO groupDTO);
	public List<CardBoardDTO> getCardBoardList();
	public List<String> getLocationList(String location);

}

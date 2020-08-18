package cardBoard.service;

import java.util.List;

import cardBoard.bean.CardBoardDTO;

public interface CardBoardService {
	public void regist(CardBoardDTO groupDTO);
	public List<CardBoardDTO> getCardBoardList();
	public List<String> getLocationList(String location);

}

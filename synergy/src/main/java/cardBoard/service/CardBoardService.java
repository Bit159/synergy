package cardBoard.service;

import java.util.List;
import java.util.Map;

import cardBoard.bean.CardBoardDTO;

public interface CardBoardService {
	public void regist(CardBoardDTO groupDTO);
	public List<CardBoardDTO> getCardBoardList();
	public List<String> getLocationList(String location);
	public List<CardBoardDTO> searchCard(List<Object> list);
	public List<CardBoardDTO> searchCardNoloc(String topic);
	public CardBoardDTO getCardContent(int seq);

}

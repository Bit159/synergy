package cardBoard.service;

import java.util.List;
import java.util.Map;

import cardBoard.bean.CardBoardDTO;
import cardBoard.bean.CardBoardPaging;

public interface CardBoardService {
	public void regist(CardBoardDTO groupDTO);
	public List<CardBoardDTO> getCardBoardList(CardBoardPaging paging);
	public List<String> getLocationList(String location);
	public List<CardBoardDTO> searchCard(List<Object> list);
	public List<CardBoardDTO> searchCardNoloc(String topic);
	public CardBoardDTO getCardContent(int seq);
	public int getBoardListCnt();
}

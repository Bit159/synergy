package cardBoard.service;

import java.util.List;
import java.util.Map;

import cardBoard.bean.CardBoardDTO;
import cardBoard.bean.CardBoardPaging;
import cardBoard.bean.CardBoardReplyDTO;

public interface CardBoardService {
	public void regist(CardBoardDTO groupDTO);
	public List<CardBoardDTO> getCardBoardList(CardBoardPaging paging);
	public List<String> getLocationList(String location);
	public List<CardBoardDTO> searchCard(List<Object> list);
	public List<CardBoardDTO> searchCardNoloc(String topic);
	public CardBoardDTO getCardContent(int seq);
	public int getBoardListCnt();
	public List<CardBoardReplyDTO> getReplyList(int seq);
	public void writeReply(CardBoardReplyDTO dto);
	public void deleteReply(int rseq);
	public void modifyReply(CardBoardReplyDTO dto);
	public void replyCntup(CardBoardDTO cardDTO);
	public void replyCntdown(CardBoardDTO cardDTO);
}

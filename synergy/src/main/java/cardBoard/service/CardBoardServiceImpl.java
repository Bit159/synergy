package cardBoard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cardBoard.bean.CardBoardDTO;
import cardBoard.dao.CardBoardDAO;
import member.bean.MemberDTO;
import member.dao.MemberDAO;

@Service
public class CardBoardServiceImpl implements CardBoardService {
	@Autowired
	private CardBoardDAO cardBoardDAO;
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	@Override
	public void regist(CardBoardDTO groupDTO) {
		 cardBoardDAO.regist(groupDTO);
	}
	@Override
	public List<CardBoardDTO> getCardBoardList() {
		return cardBoardDAO.getCardBoardList();
	}
	@Override
	public List<String> getLocationList(String location) {
		return cardBoardDAO.getLocationList(location);
	}
	@Override
	public List<CardBoardDTO> searchCard(List<Object> list) {
		return cardBoardDAO.searchCard(list);
	}
	@Override
	public List<CardBoardDTO> searchCardNoloc(String topic) {
		return cardBoardDAO.searchCardNoloc(topic);
	}
	@Override
	public CardBoardDTO getCardContent(int seq) {
		return cardBoardDAO.getCardContent(seq);
	}
	
}

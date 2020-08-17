package cardBoard.service;

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
	


}

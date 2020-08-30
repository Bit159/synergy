package user;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import notifier.NotDTO;

@Repository
@Transactional
@Setter
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private UserDTO dto;
	
	// FOR USER
	@Override
	public int insert(UserDTO userDTO) {
		return sqlSession.insert("userSQL.insert", userDTO);
	}

	@Override
	public UserDTO selectOne(String id) {
		try {
			dto = sqlSession.selectOne("userSQL.selectOne", id);
		}catch (Exception e) {
			dto = null;
		}
		return dto;
	}

	@Override
	public int update(UserDTO userDTO) {
		return sqlSession.update("userSQL.update", userDTO);
	}

	@Override
	public int delete(UserDTO userDTO) {
		return sqlSession.delete("userSQL.delete", userDTO);
	}

	@Override
	public List<UserDTO> selectAllFromUsertable() {
		return sqlSession.selectList("userSQL.selectAllFromUsertable");
	}
	// End of FOR USER
	
	// FOR MAP
	@Override
	public int getMycareer(String username) {
		return sqlSession.selectOne("userSQL.getMycareer", username);
	}
	
	@Override
	public int insertMatch(MatchDTO matchDTO) {
		return sqlSession.insert("userSQL.insertMatch", matchDTO);
	}

	@Override
	public List<MatchDTO> getListFromMatch() {
		return sqlSession.selectList("userSQL.getListFromMatch");
	}

	@Override
	public int deleteMatched(List<MatchDTO> list) {
		return sqlSession.delete("userSQL.deleteMatched", list);
	}

	@Override
	public int deleteMatch(MatchDTO matchDTO) {
		return sqlSession.delete("userSQL.deleteMatch", matchDTO);
	}
	// End of FOR MAP
	
	
	// FOR CRAWL
	@Override
	public int crawlInsert(List<CBoardDTO> list) {
		return sqlSession.insert("userSQL.crawlInsert", list);
	}

	@Override
	public List<String> getEmptyContentBno() {
		return sqlSession.selectList("userSQL.getEmptyContentBno");
	}

	@Override
	public int insertContents(List<CBoardDTO> list) {
		return sqlSession.insert("userSQL.insertContents", list);
	}

	@Override
	public int getGreatestBno() {
		return sqlSession.selectOne("userSQL.getGreatestBno");
	}

	// End of FOR CRAWL

	// FOR CRAWAL BOARD
	@Override
	public List<CBoardDTO> getBoardList() {
		return sqlSession.selectList("userSQL.getBoardList");
	}

	// END OF  CRAWAL BOARD

	
	// FOR Notification function
	@Override
	public List<NotDTO> getOnTimeList() {
		return sqlSession.selectList("userSQL.getOnTimeList");
	}
	@Override
	public Date getDBTime() {
		return sqlSession.selectOne("userSQL.getDBTime");
	}

	// End of Notification Methods
	
	// FOR DISPLAYING SCHEDULES
	@Override
	public List<NotDTO> getSchedules() {
		return sqlSession.selectList("userSQL.getSchedules");
	}

	@Override
	public int deleteSchedule(int no) {
		return sqlSession.delete("userSQL.deleteSchedule", no);
	}
	// END OF SCHEDULES
}

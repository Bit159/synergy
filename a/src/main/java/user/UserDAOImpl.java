package user;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;

@Repository("userDAO")
@Transactional
@Setter
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private UserDTO dto;
	
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

	@Override
	public int insertMatch(MatchDTO matchDTO) {
		return sqlSession.insert("userSQL.insertMatch", matchDTO);
	}

	@Override
	public List<MatchDTO> selectAllFromMatch() {
		return sqlSession.selectList("userSQL.selectAllFromMatch");
	}

	@Override
	public int deleteMatched(List<MatchDTO> list) {
		return sqlSession.delete("userSQL.deleteMatched", list);
	}

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
	
	

}

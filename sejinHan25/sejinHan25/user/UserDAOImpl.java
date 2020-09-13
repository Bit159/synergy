package sejinHan25.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import sejinHan25.board.CBoardDTO;

@Repository("userDAO")
@Transactional
@Setter
public class UserDAOImpl implements UserDAO {
   
   @Autowired
   private SqlSession sqlSession;
   
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
   public List<UserDTO> selectAll() {
      return sqlSession.selectList("userSQL.selectAll");
   }

	@Override
	public UserDTO login(String id, String pwd) {
		Map<String, String> map = new HashMap<String, String>();
		System.out.println("dao "+ id);
		map.put("id", id);
		map.put("pwd", pwd);
		UserDTO userDTO = sqlSession.selectOne("userSQL.login",map);
		return userDTO;
	}

	@Override
	public List<CBoardDTO> getCBoardList() {
		return sqlSession.selectList("userSQL.getCBoardList");
	}

   
   

}
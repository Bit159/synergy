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
   public List<UserDTO> selectAll() {
      return sqlSession.selectList("userSQL.selectAll");
   }

   
   

}
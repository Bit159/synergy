package sejinHan25.user;

import java.util.List;

import sejinHan25.board.CBoardDTO;

public interface UserDAO {
	public abstract int insert(UserDTO userDTO);

	public abstract int update(UserDTO userDTO);

	public abstract int delete(UserDTO userDTO);

	public abstract List<UserDTO> selectAll();

	public abstract UserDTO selectOne(String id);

	public abstract UserDTO login(String id, String pwd);
	
	public abstract List<CBoardDTO> getCBoardList();

}

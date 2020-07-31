package user;

import java.util.List;

public interface UserDAO {
	public abstract int insert(UserDTO userDTO);
	public abstract int update(UserDTO userDTO);
	public abstract int delete(UserDTO userDTO);
	public abstract List<UserDTO> selectAll();
	public abstract UserDTO selectOne(String id);
	public abstract int insertMatch(MatchDTO matchDTO);
}

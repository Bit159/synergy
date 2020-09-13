package kkh1595.user.details;

import java.util.List;

public interface UserMapper {
	public UserDetailsVO readUser(String username);
	public List<String> readAuthority(String username);
}

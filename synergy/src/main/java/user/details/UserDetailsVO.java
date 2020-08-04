package user.details;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Data;

@Data
public class UserDetailsVO implements UserDetails {
	private static final long serialVersionUID = 1L;
	private String username; // ID
	private String password; // PW
	private List<GrantedAuthority> authorities;
	
	//한 객체당 하나의 권한, 실질적으로 String 타입의 권한명만 객체에 담아주면 된다.
	public void setAuthorities(List<String> authList) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (int i = 0; i < authList.size(); i++) {
			authorities.add(new SimpleGrantedAuthority(authList.get(i)));
		}

		this.authorities = authorities;
	}
	
	@Override
	//id
	public String getUsername() {
		return username;
	}

	@Override
	//password
	public String getPassword() {
		return password;
	}

	@Override
	//권한
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	//계정이 만료 되지 않았는지?
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	//계정이 잠기지 않았는지?
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	// 패스워드가 만료되지 않았는가?
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	// 계정이 활성화 되었는가?
	public boolean isEnabled() {
		return true;
	}

}

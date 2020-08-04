package user.details;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public interface UserDetails {
	public String getUsername();
	public String getPassword();
	public Collection<? extends GrantedAuthority> getAuthorities();
	public boolean isAccountNonExpired();
	public boolean isAccountNonLocked();
	public boolean isCredentialsNonExpired();
	public boolean isEnabled();
}

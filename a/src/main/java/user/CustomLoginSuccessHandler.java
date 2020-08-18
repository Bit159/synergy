package user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

// 이 클래스를 사용하는 곳은 security-context.xml이다 거기서 bean 선언할 때 이름을 석세스 핸들러의 ref 값으로 주는 것이다.
// 성공하면 여기에 와서 로그인 했다고 sysout을 찍고
// 인증결과 객체인 authentication에서 getAuthorities를 이용, 인증 결과에서 갖고 있는 모든 권한을 forEach를 이용해 각각의 리턴 authority를 리스트에 담는다.
// 그 리스트에 admin이 포함되어 있으면  member로 가고 
// <security:form-login login-page="/mylogin" authentication-success-handler-ref="customLoginSuccess" />
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		
		System.out.println("Login Success");
		
		List<String> roleNames = new ArrayList<>();
		
		//인증객체 authentication에서 getAuthorities 메소드로 모든 권한을 가져오고
		//forEach로 각각의 authority에 대해서 리스트에 authority.getAuthority 메소드로 호출된 권한 값을 담는다.
		authentication.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		
		System.out.println("Role Name : " + roleNames);
		
		// 담은 리스트에 어드민이 있으면 map으로 보낸다
		if(roleNames.contains("ROLE_ADMIN")) {
			//response.sendRedirect("/admin_map");
			return;
		}
		
		// 어드민은 없는데 멤버는 있으면 board로 보낸다
		if(roleNames.contains("ROLE_MEMBER")) {
			//response.sendRedirect("/board");
			return;
		}
		
		// 어드민도, 멤버도 없으면 /로 보낸다
		response.sendRedirect("/");
		
	}

}

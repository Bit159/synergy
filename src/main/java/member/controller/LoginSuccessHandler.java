package member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		List<String> roleNames = new ArrayList<>(); 
		
		authentication.getAuthorities().forEach(authority -> { 
			roleNames.add(authority.getAuthority());
		});
		
		System.out.println(roleNames.get(0));
		
		if(roleNames.contains("ROLE_ADMIN")) { 
			response.sendRedirect("/synergy/admin/admin_index"); 
			return; 
		} 
		
		if(roleNames.contains("ROLE_MEMBER")) { 
			response.sendRedirect("/synergy/member/myPage");
			return;
		} 
		
		response.sendRedirect("/"); 
		
		
	}

}

package member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;

import member.service.MemberService;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	@Autowired
	MemberService memberService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//		List<String> roleNames = new ArrayList<>(); 
//		
//		authentication.getAuthorities().forEach(authority -> { 
//			roleNames.add(authority.getAuthority());
//		});
//		
//		
//		if(roleNames.contains("ROLE_ADMIN")) { 
//			response.sendRedirect("/synergy/admin/admin_index"); 
//			return; 
//		} 
//		
//		if(roleNames.contains("ROLE_MEMBER")) { 
//			response.sendRedirect("/synergy/member/myPage");
//			return;
//		} 
//		
//		response.sendRedirect("/");
		String username = request.getParameter("username");
		String nickname = memberService.getNickname(username);
		System.out.println(nickname);
		
		HttpSession session = request.getSession();
		session.setAttribute("nickname", nickname);
		response.sendRedirect("/all/welcome");
		
	}

}

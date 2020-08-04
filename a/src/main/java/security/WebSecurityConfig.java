//package security;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
//		http.httpBasic()
//		.and()
//		.authorizeRequests()
//			.antMatchers("/users/{userId}").access("@authenticationCheckHandler.checkUserId(authentication,#userId)")
//			.antMatchers("/admin/db/**").access("hasRole('ADMIN_MASTER') or hasRole('ADMIN') and hasRole('DBA')")
//			.antMatchers("/register/**").hasRole("ANONYMOUS")
//		.and()
//		.formLogin()
//			.loginPage("/login")
//			.usernameParameter("email")
//			.passwordParameter("password")
//			.successHandler(successHandler())
//			.failureHandler(failureHandler())
//			.permitAll();
//	}
//	
//	public AuthenticationSuccessHandler successHandler() {
//		return null;
//		
//	}
//	public AuthenticationFailureHandler failureHandler() {
//		return null;
//		
//	}
//}

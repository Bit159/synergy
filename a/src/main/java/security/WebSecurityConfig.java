package security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/guest/**").permitAll().antMatchers("/manager/**").hasRole("MANAGER")
				.antMatchers("/admin/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/map").denyAll();
		http.formLogin();
		http.exceptionHandling().accessDeniedPage("/");
		http.logout().logoutUrl("/").invalidateHttpSession(true);

//		
//		http.httpBasic()
//			.and()
//			.authorizeRequests()
//				.antMatchers("/users/{userId}").access("@authenticationCheckHandler.checkUserId(authentication,#userId)")
//				.antMatchers("/a/map").access("hasRole('ADMIN_MASTER') or hasRole('ADMIN') and hasRole('DBA')")
//				.antMatchers("/map").access("hasRole('ADMIN_MASTER') or hasRole('ADMIN') and hasRole('DBA')")
//				.antMatchers("/register/**").hasRole("ANONYMOUS")
//			.and()
//			.formLogin()
//				.loginPage("/login")
//				.usernameParameter("email")
//				.passwordParameter("password");
	}

	@Bean
	public ApplicationInitializer applicationInitializer() {
		return new ApplicationInitializer();
	}

	@Bean
	public SecurityWebApplicationInitializer securityWebApplicationInitializer() {
		return new SecurityWebApplicationInitializer();
	}

}

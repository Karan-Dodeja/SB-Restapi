package com.mobilapi.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.mobilapi.service.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfiguration {

	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		//		http.csrf().disable()
		// .antMatcher(HttpMethod.POST, "/users")
		// 	.permitAll()
		//	.anyRequest().authenticated();
		
		AuthenticationManagerBuilder authnticationmanagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authnticationmanagerBuilder.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
		
		AuthenticationManager authenticationManager = authnticationmanagerBuilder.build(); 
		
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager);
		authenticationFilter.setFilterProcessesUrl("/users/login");
		
		http
		.cors().and()
		.csrf().disable().authorizeHttpRequests()
		.requestMatchers(HttpMethod.POST, SecurityConstants.SIGN_U_URL)
		.permitAll()
		.anyRequest().authenticated()
		.and()
		.authenticationManager(authenticationManager)
		.addFilter(new AuthenticationFilter(authenticationManager))
		;
		
		return http.build();
		
	}
	
	// public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	// }
}

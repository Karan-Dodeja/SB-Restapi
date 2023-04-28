package com.mobilapi.security;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobilapi.SpringApplicationContext;
import com.mobilapi.dto.UserDto;
import com.mobilapi.model.UserLoginRequestModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKey;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
		try {
			UserLoginRequestModel creds = null;
			try {
				creds = new ObjectMapper().readValue(req.getInputStream(), UserLoginRequestModel.class);
			} catch (java.io.IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
		
		byte[] secretkeys = Base64.getEncoder().encode(SecurityConstants.TOKEN_SECRET.getBytes());
		SecretKey secretkey = new SecretKeySpec(secretkeys, SignatureAlgorithm.HS512.getJcaName());
		Instant now = Instant.now();

		String userName = ((User) auth.getPrincipal()).getUsername();
		String token = Jwts.builder()
				.setSubject(userName)
				.setExpiration(
							Date.from(now.plusMillis(SecurityConstants.EXPIRATION_TIME))
							)
				.setIssuedAt(Date.from(now)).signWith(secretkey, SignatureAlgorithm.HS512).compact();
		
	//	UserService userService= (UserService)SpringApplicationContext.getbean("userServiceImpl");
		
				res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		// 		res.addHeader("UserId", UserDto.getUserId());
	}
}

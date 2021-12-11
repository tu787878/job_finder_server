package de.tcg.jobFinder.service.impl;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tcg.jobFinder.util.JwtUtil;

@Service
public abstract class UntilService {
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	public String toToken(HttpServletRequest request) {
		final String authorizationHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = null;
 
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username = jwtTokenUtil.extractUsername(jwt);
		}
		
		if(jwt != null) {
			return jwt;
		}
		
		return null;
	}
	
	public String generateRandomId(int len) {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
          +"lmnopqrstuvwxyz";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}

}

package com.company.ac.tokenservice;

import java.security.Key;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.company.ac.models.User;
import com.company.ac.models.company.Company;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

public class JwtTokenGenerator {

	private User user;
	
	private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		
	private static final String USER_EMAIL = "email";
	
	public JwtTokenGenerator() {}
	
	public JwtTokenGenerator (User user) {
		this.user = user;		
	}
	
	public String generate() {
		
		return Jwts.builder().
				setSubject("Account-User")
				.claim(USER_EMAIL, user.getEmail())
				.signWith(key)
				.compact();		
		
	}
	
	public static boolean validate(String token) {
		boolean result = false;
		try {		
			Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();			
			result = true;			
		} catch (SignatureException | ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
				| IllegalArgumentException e) {
			e.printStackTrace();
		}			
		return result;
	}	
	
}

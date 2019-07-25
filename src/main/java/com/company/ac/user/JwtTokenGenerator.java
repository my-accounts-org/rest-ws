package com.company.ac.user;

import java.security.Key;

import com.company.ac.models.User;

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
	
	public JwtTokenGenerator (User user) {
		this.user = user;		
	}
	
	public String generate() {
		
		return Jwts.builder().
				setSubject("Account-User")
				.claim("name", user.getEmail())
				.claim("company", user.getCompany())
				.signWith(key)
				.compact();		
		
	}
	
	public static boolean validate(String token) {
		System.out.println("JwtTokenValidator: "+token);
		try {		
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			return true;
		} catch (SignatureException | ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
				| IllegalArgumentException e) {
			e.printStackTrace();
		}			
		return false;
	}
}

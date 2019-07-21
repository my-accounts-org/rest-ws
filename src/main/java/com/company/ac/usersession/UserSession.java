package com.company.ac.usersession;

import java.security.Key;
import java.util.Base64;

import com.company.ac.models.User;
import com.company.ac.models.UserToken;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

public class UserSession {

	private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	public static String encode(User user) {
		String token = null;
		
		try {
			
			token = Jwts.builder().
					setSubject("Joe")
					.claim("name", user.getEmail())
					.claim("password", Base64.getEncoder().encode(user.getPassword().getBytes()))
					.signWith(key).
					compact();
		} catch (InvalidKeyException e) {			
			e.printStackTrace();
		}
		
		return token; 
	}
	
	
	public static String decode(UserToken token) {
		try {
			
			Jwts.parser().require("password", "vivek1").setSigningKey(key).parseClaimsJws(token.getToken());
			System.out.println("Verified.....!!!!");
		} catch (SignatureException | ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
				| IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

package com.bayram.auth;

import java.security.Key;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenManager {
	
	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final static int validaty = 5 * 60 * 1000;

	public String generateToken(String useName) {

		return Jwts.builder()
				.setSubject(useName)
				.setIssuer("www.sbayram.com")
				.setIssuedAt((new Date(System.currentTimeMillis())))
				.setExpiration(new Date(System.currentTimeMillis() + validaty))
				.signWith( key)
				.compact();

	}

	public String getUserNameFromToken(String token) {

		Claims claims = getClaims(token);

		return claims.getSubject();

	}

	public boolean validateToken(String token) {

		if (getUserNameFromToken(token) != null && isExpired(token)) {
			return true;
		}
		return false;
	}

	private boolean isExpired(String token) {

		Claims claims = getClaims(token);

		return claims.getExpiration().before(new Date(System.currentTimeMillis()));
	}

	private Claims getClaims(String token) {
		@SuppressWarnings("deprecation")
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		return claims;
	}

}

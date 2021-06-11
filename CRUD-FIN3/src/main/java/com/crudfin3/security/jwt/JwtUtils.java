package com.crudfin3.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.crudfin3.security.service.UserDetailsImpl;

import io.jsonwebtoken.*;

/**
 * generateJwtToken
 *
 * Version 1.0
 *
 * Date: 08-06-2021
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE               AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 08-06-2021         LONGTB4           Create
 */
@Component
public class JwtUtils {
	
	private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);
	
	// This JWT_SECRET segment is secret, only the server side knows
	private static final String jwtSecret = "tabiloSecretKey";

	//Effective time of string jwt (2 hours)
	private static final int jwtExpirationMs = 7200000;

	/**
	 * generate token
	 * 
	 * @param authentication
	 * @return Jwt
	 */
	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		
		// Generate web token json string from user id.
		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	/**
	 * get username by token
	 * 
	 * @param token
	 * @return username
	 */
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * validate token
	 * 
	 * @param authToken
	 * @return boolean
	 */
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
		
	}
}
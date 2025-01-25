package com.rodolfo.infrastructure.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

    private final String secretKey = "sua-chave-secreta-super-segura-que-deve-ser-bem-longa";

    @SuppressWarnings("deprecation")
	public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    @SuppressWarnings("deprecation")
	public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8))) 
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractEmailByToken(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
    
    public String extractEmailByToken(String token) {
    	return extractClaims(token).getSubject();
    }
    
    
    
    
    
    
    
    
}

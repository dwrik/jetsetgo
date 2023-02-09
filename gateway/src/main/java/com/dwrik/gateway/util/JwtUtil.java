package com.dwrik.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private Key signingKey;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.token.validity}")
    private long tokenValidity;

    @PostConstruct
    public void init() {
        signingKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(Integer id, String email) {
        long issuedAt = System.currentTimeMillis();
        long expiration = issuedAt + (tokenValidity * 1000);
        return Jwts.builder()
                .setClaims(Map.of("userId", id, "email", email))
                .setIssuedAt(new Date(issuedAt))
                .setExpiration(new Date(expiration))
                .signWith(signingKey)
                .compact();
    }

    public Claims getAllClaims(final String token) {
        return Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenInvalid(final String token) {
        try {
            getAllClaims(token);
            return false;
        } catch (JwtException ignored) {
            return true;
        }
    }
}

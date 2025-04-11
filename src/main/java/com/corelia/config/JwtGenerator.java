package com.corelia.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtGenerator {

    @Value("${home4u.secret.key}")
    private String secretKey;

    @Value("${auth.expiration}")
    private Long tokenExpiration;

    @Value("${auth.refresh.expiration}")
    private Long refreshTokenExpiration;

    public String generateToken(Long userId, boolean isRefresh) {

        return Jwts.builder()
                    .issuer("Corelia")
                    .subject(userId.toString())
                    .issuedAt(new Date())
                    .expiration(generateExpirationDate(isRefresh))
                    .signWith(getKey())
                    .compact();
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    private Date generateExpirationDate(boolean isRefresh) {
        return isRefresh  ?
                  new Date(System.currentTimeMillis() + (refreshTokenExpiration * 1000))
                  : new Date(System.currentTimeMillis() + (tokenExpiration * 1000) );
    }
}

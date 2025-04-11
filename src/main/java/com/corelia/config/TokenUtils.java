package com.corelia.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class TokenUtils {

    @Value("${home4u.secret.key}")
    private String secretKey;


    public Long validateToken(String token, HttpServletResponse response) {

        try {
             Claims claims =Jwts
                    .parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token).getPayload();
            return Long.parseLong(claims.getSubject());
        }catch (ExpiredJwtException e){
            response.addHeader("X-Auth-Token-Status", "Expired");
        }
        return null;
    }

    public Long validateToken(String token) {
        Claims claims =Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Long.parseLong(claims.getSubject());
    }


    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}

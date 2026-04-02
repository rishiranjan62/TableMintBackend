package com.tablemint.auth;

import com.tablemint.config.JwtProperties;
import com.tablemint.model.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {


    private static final String HMAC_ALG = "HmacSHA256";

    private final JwtProperties props;

    private SecretKey signingKey() {
        byte[] keyBytes = props.getJwtSecret().getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(keyBytes, HMAC_ALG);
    }

    public String generateToken(Long userId, String phoneNumber, UserRole role) {
        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("phone", phoneNumber)
                .claim("role", role.name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + props.getJwtExpirationMs()))
                .signWith(signingKey())
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(signingKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Long getUserIdFromToken(String token) {
        return Long.parseLong(parseToken(token).getSubject());
    }

    public UserRole getRoleFromToken(String token) {
        String role = parseToken(token).get("role", String.class);
        return role != null ? UserRole.valueOf(role) : UserRole.CUSTOMER;
    }


}

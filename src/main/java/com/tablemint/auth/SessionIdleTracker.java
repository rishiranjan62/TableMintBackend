package com.tablemint.auth;

import com.tablemint.config.JwtProperties;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class SessionIdleTracker {
    private final JwtProperties props;
    private final JwtUtil jwtUtil;
    private final ConcurrentHashMap<String, Long> lastActivityMillis = new ConcurrentHashMap<>();

    /**
     * @return true if the request may proceed; false if idle limit exceeded (caller should 401).
     */
    public boolean touchOrRejectIfIdle(String token) {
        long idleMs = props.getSessionIdleMs();
        if (idleMs <= 0) {
            return true;
        }
        Claims claims = jwtUtil.parseToken(token);
        Date issuedAt = claims.getIssuedAt();
        if (issuedAt == null) {
            return true;
        }
        String key = claims.getSubject() + ":" + issuedAt.getTime();
        long now = System.currentTimeMillis();
        Long last = lastActivityMillis.get(key);
        if (last != null && now - last > idleMs) {
            return false;
        }
        lastActivityMillis.put(key, now);
        return true;
    }
}

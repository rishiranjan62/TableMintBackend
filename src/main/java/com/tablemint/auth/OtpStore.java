package com.tablemint.auth;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


/**
 * In-memory OTP store. Key = restaurant scope + phone so the same number can have different OTs per restaurant.
 * {@code restaurantId} null = legacy single-tenant scope.
 */

@Component
public class OtpStore {
    private final Map<String, OtpRecord> store = new ConcurrentHashMap<>();

    public void put(String phoneNumber, Long restaurantId, String code, Instant expiresAt) {
        store.put(key(phoneNumber, restaurantId), new OtpRecord(code, expiresAt));
    }

    public Optional<String> getAndRemoveIfValid(String phoneNumber, Long restaurantId, String code) {
        String k = key(phoneNumber, restaurantId);
        OtpRecord record = store.get(k);
        if (record == null || !record.code.equals(code) || Instant.now().isAfter(record.expiresAt)) {
            return Optional.empty();
        }
        store.remove(k);

        return Optional.of(record.code);
    }

    public void removeString(String phoneNumber, Long restaurantId) {
        store.remove(key(phoneNumber, restaurantId));
    }

    private static String key(String phone, Long restaurantId) {
        String p = phone != null ? phone.trim() : "";
        String r = restaurantId != null ? "r" + restaurantId : "_";
        return r + "|" + p;
    }

    private record OtpRecord(String code, Instant expiresAt) {

    }


}

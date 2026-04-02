package com.tablemint.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * In-memory store of recent customer OTP requests so Floor Manager / Admin can
 * see and share OTPs with customers. Entries expire after a short window.
 */
@Component
@Slf4j
public class RecentCustomerOtpStore {
    private static final int DEFAULT_MAX_ENTRIES = 30;
    private static final long DEFAULT_TTL_SECONDS = 600; // 10 minutes

    private final int maxEntries;
    private final long ttlSeconds;
    private final List<Entry> entries = new CopyOnWriteArrayList<>();

    public RecentCustomerOtpStore(
            @Value("${app.otp.recent-display-max:30}") int maxEntries,
            @Value("${app.otp.recent-display-ttl-seconds:600}") long ttlSeconds) {
        this.maxEntries = maxEntries > 0 ? maxEntries : DEFAULT_MAX_ENTRIES;
        this.ttlSeconds = ttlSeconds > 0 ? ttlSeconds : DEFAULT_TTL_SECONDS;
    }

    public void add(String phoneNumber, String otp, String tableNumber, Long restaurantId) {
        Entry e = new Entry(phoneNumber.trim(), otp, Instant.now(),
                tableNumber != null ? tableNumber.trim() : null, restaurantId);
        entries.add(0, e);
        prune();
    }

    /**
     * Recent OTP requests for this restaurant only (or legacy entries when {@code restaurantId} is null for super-admin).
     */
    public List<Entry> getRecentForRestaurant(Long restaurantId) {
        Instant cutoff = Instant.now().minusSeconds(ttlSeconds);
        List<Entry> out = new ArrayList<>();
        for (Entry e : entries) {
            if (e.requestedAt.isBefore(cutoff)) break;
            if (restaurantId == null) {
                if (e.restaurantId == null) {
                    out.add(e);
                }
            } else if (restaurantId.equals(e.restaurantId)) {
                out.add(e);
            }
        }
        return out;
    }

    /**
     * Super-admin (no tenant): see all recent entries.
     */
    public List<Entry> getRecentAll() {
        Instant cutoff = Instant.now().minusSeconds(ttlSeconds);
        List<Entry> out = new ArrayList<>();
        for (Entry e : entries) {
            if (e.requestedAt.isBefore(cutoff)) break;
            out.add(e);
        }
        return out;
    }

    private void prune() {
        Instant cutoff = Instant.now().minusSeconds(ttlSeconds);
        entries.removeIf(e -> e.requestedAt.isBefore(cutoff));
        while (entries.size() > maxEntries) {
            entries.remove(entries.size() - 1);
        }
    }

    public record Entry(String phoneNumber, String otp, Instant requestedAt, String tableNumber, Long restaurantId) {
    }
}

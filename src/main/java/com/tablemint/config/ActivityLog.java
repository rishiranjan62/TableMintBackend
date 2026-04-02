package com.tablemint.config;

import org.slf4j.MDC;

/**
 * Helper to record the current request's business activity for logging.
 * Set in controllers or services; the request log line will include it.
 * Keys are cleared after the request is logged.
 */
public class ActivityLog {

    public static final String ACTIVITY = "activity";
    public static final String ACTIVITY_DETAIL = "activityDetail";

    private ActivityLog() {
    }

    /**
     * Record what activity was performed (e.g. "Operating hours updated").
     */
    public static void setActivity(String activity) {
        setActivity(activity, null);
    }

    /**
     * Record activity and detail (e.g. "Operating hours updated", "restaurantId=5, 09:00-22:00").
     */
    public static void setActivity(String activity, String detail) {
        if (activity != null && !activity.isBlank()) {
            MDC.put(ACTIVITY, activity.trim());
        }
        if (detail != null && !detail.isBlank()) {
            MDC.put(ACTIVITY_DETAIL, detail.trim());
        }
    }

    /**
     * Clear activity keys (called by RequestLoggingFilter after logging).
     */
    public static void clear() {
        MDC.remove(ACTIVITY);
        MDC.remove(ACTIVITY_DETAIL);
    }


}

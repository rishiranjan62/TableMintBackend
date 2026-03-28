package com.tablemint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionNoticeDto {
    private String billingPhase;
    /** ISO-8601 instant string or null. */
    private String accessPeriodEndsAt;
    /** Whole days remaining until access end (Asia/Kolkata calendar), null if N/A. */
    private Integer daysRemaining;
    /** True when 1-3 days left on trial or paid period. */
    private boolean showRenewalWarning;
    private boolean trialActive;
    /** True when billing phase is EXPIRED or access end passed and tenant is restricted. */
    private boolean accessExpired;
}

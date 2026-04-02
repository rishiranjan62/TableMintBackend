package com.tablemint.dto;

import com.tablemint.model.SubscriptionPlan;
import com.tablemint.model.TenantBillingPhase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformRestaurantDto {

    private Long id;

    private Long organizationId;
    private String organizationName;
    private String organizationSlug;

    private String name;
    private String slug;
    private String phone;
    private String city;
    private Instant createdAt;
    private String openTime;
    private String closeTime;
    private Boolean enabled;

    private SubscriptionPlan subscriptionPlan;
    private Boolean featureStaffManagement;
    private Boolean featureInventoryBasic;
    private Boolean featureInventoryFulL;
    private Boolean featureValueAdded;
    private Boolean featureTableSharingValueAdded;

    /**
     * Effective access after applying plan limits to flags.
     */
    private Boolean effectiveStaffManagement;
    private Boolean effectiveInventoryBasic;
    private Boolean effectiveInventoryFulL;
    private Boolean effectiveValueAdded;
    private Boolean effectiveTableSharingValueAdded;
    private Integer maxStaffSeats;

    private TenantBillingPhase billingPhase;
    /**
     * ISO-8601 end of trial or current paid window.
     */
    private String accessPeriodEndsAt;

    /**
     * Earliest-created active ADMIN for renewal / support outreach (may be null).
     */
    private Long primaryAdminUserId;
    private String primaryAdminName;
    private String primaryAdminPhone;
    private String primaryAdminEmail;
}

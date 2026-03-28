package com.tablemint.dto;

import com.tablemint.model.SubscriptionPlan;
import lombok.Data;

import java.time.Instant;

@Data
public class PlatformRestaurantUpdateDto {

    /**
     * When false, restaurant is restricted: staff cannot log in.
     */
    private Boolean enabled;

    private SubscriptionPlan subscriptionPLan;
    private Boolean featureStaffManagement;
    private Boolean featureInventoryBasic;
    private Boolean featureInventoryFulL;
    private Boolean featureValueAdded;

    /**
     * Add days to access end (trial or paid). Re-enables an expired tenant as trial with full Pro flags.
     */
    private Integer extendAccessDays;
    /**
     * When true, assign {@Link com. tableMint.model. TenantBilLingPhase#TRIAL): 15 days from now, Pro + all feature 1
     * Ignores {@Link #subscriptionPlan} / feature fields for this save.
     */
    private Boolean assignTrialAccess;
    /**
     * Explicit access window end (trial or paid). Applied last if set; for platform testing / support.
     * ISO-8601 instant (JSON string).
     */
    private Instant accessPeriodEndsAt;
}

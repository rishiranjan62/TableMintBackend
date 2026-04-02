package com.tablemint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Effective capabilities for a tenant
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntitlementsDto {
    private SubscriptionPlan subscriptionPlan;

    /**
     * Max active staff seats for this tenant (5 on Starter or when staff management is off on higher tiers).
     */
    private int maxStaffSeats;
    private boolean staffManagement;
    private boolean inventoryBasic;
    private boolean inventoryFull;
    private boolean valueAdded;
    private boolean tableSharingValueAdded;
}

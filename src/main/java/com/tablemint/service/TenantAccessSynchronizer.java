package com.tablemint.service;

import com.tablemint.config.ActivityLog;
import com.tablemint.model.Restaurant;
import com.tablemint.model.SubscriptionPlan;
import com.tablemint.model.TenantBillingPhase;
import com.tablemint.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * When a trial or paid access window ends , restricts the tenant and revokes premium features.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class TenantAccessSynchronizer {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void synchronize(Long restaurantId) {
        if (restaurantId == null) {
            return;
        }
        restaurantRepository.findById(restaurantId).ifPresent(this::expireIfDue);
    }

    private void expireIfDue(Restaurant r) {
        TenantBillingPhase phase = r.getBillingPhase() != null ? r.getBillingPhase() : TenantBillingPhase.NONE;
        if (phase != TenantBillingPhase.TRIAL && phase != TenantBillingPhase.PAID) {
            return;
        }
        if (r.getAccessPeriodEndsAt() == null) {
            return;
        }
        if (!Instant.now().isAfter(r.getAccessPeriodEndsAt())) {
            return;
        }
        r.setEnabled(false);
        r.setBillingPhase(TenantBillingPhase.EXPIRED);
        r.setSubscriptionPlan(SubscriptionPlan.STARTER);
        r.setFeatureStaffManagement(false);
        r.setFeatureInventoryBasic(false);
        r.setFeatureInventoryFull(false);
        r.setFeatureValueAdded(false);
        r.setFeatureTableSharingValueAdded(false);
        restaurantRepository.save(r);
        log.info("Tenant access expired: restaurantId={}, name={}", r.getId(), r.getDisplayName());
        ActivityLog.setActivity("Tenant access period ended - restricted",
                "restaurantId=" + r.getId() + ", name=" + r.getName());
    }


}

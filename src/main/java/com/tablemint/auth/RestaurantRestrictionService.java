package com.tablemint.auth;

import com.tablemint.exception.ForbiddenException;
import com.tablemint.model.User;
import com.tablemint.model.UserRole;
import com.tablemint.repository.RestaurantRepository;
import com.tablemint.repository.TableOccupancyRepository;
import com.tablemint.service.TenantAccessSynchronizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Enforces {@Code Restaurant.enabled}: blocks staff of a disabled tenant and customers linked to a table there.
 * */
@Service
@RequiredArgsConstructor
public class RestaurantRestrictionService {

    private final RestaurantRepository restaurantRepository;
    private final TableOccupancyRepository occupancyRepository;
    private final TenantAccessSynchronizer tenantAccessSynchronizer;

    /** True if authenticated API access should be denied (401). */
    public boolean isBlocked(User user) {
        if (user.getRole().isPlatformInternal() && user.getRestaurantId() == null) {
            return false;
        }
        Long tenantId = user.getRestaurantId();
        if (tenantId != null) {
            tenantAccessSynchronizer.synchronize(tenantId);
            return restaurantRepository.findById(tenantId)
                    .map(r -> Boolean.FALSE.equals(r.getEnabled()))
                    .orElse(false);
        }
        if (user.getRole() == UserRole.CUSTOMER) {
            return occupancyRepository.findActiveRestaurantIdByUserId(user.getId())
                    .flatMap(rid -> {
                        if (rid == null) {
                            return Optional.empty();
                        }
                        tenantAccessSynchronizer.synchronize(rid);
                        return restaurantRepository.findById(rid);
                    })
                    .map(r -> Boolean.FALSE.equals(r.getEnabled()))
                    .orElse(false);
        }
        return false;
    }

    /** For OTP, join-table, etc.: fail if tenant is disabled. */
    public void requireRestaurantActive(Long restaurantId) {
        if (restaurantId == null) {
            return;
        }
        tenantAccessSynchronizer.synchronize(restaurantId);
        restaurantRepository.findById(restaurantId).ifPresent(r -> {
            if (Boolean.FALSE.equals(r.getEnabled())) {
                throw new ForbiddenException(
                        "This restaurant is currently restricted from using the application. Contact TableMint support.");
            }
        });
    }
}

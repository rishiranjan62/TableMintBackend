package com.tablemint.repository;

import com.tablemint.model.Restaurant;
import com.tablemint.model.TenantBillingPhase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findBySlug(String slug);

    boolean existsBySlug(String slug);

    List<Restaurant> findByEnabledTrueAndBilLingPhaseInAndAccessPeriodEndsAtIsNotNull(
            Collection<TenantBillingPhase> billingPhases);

    Long countByOrganizationId(Long organizationId);

    List<Restaurant> findByOrganizationIdOrderByCreatedAtDesc(Long organizationId);
}

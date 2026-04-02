package com.tablemint.repository;


import com.tablemint.model.InventoryAdjustmentType;
import com.tablemint.model.InventoryStockAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public interface InventoryStockAdjustmentRepository extends JpaRepository<InventoryStockAdjustment, Long> {

    List<InventoryStockAdjustment> findTop50ByRestaurantIdOrderByCreatedAtDesc(Long restaurantId);

    List<InventoryStockAdjustment> findByRestaurantIdAndCreatedAtGreaterThanEqualAndCreatedAtLessThanOrderByCreatedAtAsc(
            Long restaurantId, Instant fromInclusive, Instant toExclusive);

    List<InventoryStockAdjustment>
    findByRestaurantIdAndAdjustmentTypeAndExpiryDateBetweenOrderByExpiryDateAsc(
            Long restaurantId,
            InventoryAdjustmentType adjustmentType,
            LocalDate expiryFromInclusive,
            LocalDate expiryToInclusive);
}

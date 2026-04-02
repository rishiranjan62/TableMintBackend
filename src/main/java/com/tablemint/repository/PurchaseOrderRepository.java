package com.tablemint.repository;

import com.tablemint.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    List<PurchaseOrder> findByRestaurantIdOrderByCreatedAtDesc(Long restaurantId);

    Optional<PurchaseOrder> findByIdAndRestaurantId(Long id, Long restaurantId);

    long countByRestaurantIdAndCreatedAtGreaterThanEqual(Long restaurantId, Instant createdAt);

}

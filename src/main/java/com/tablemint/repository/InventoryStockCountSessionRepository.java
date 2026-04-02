package com.tablemint.repository;

import com.tablemint.model.InventoryStockCountSession;
import com.tablemint.model.InventoryStockCountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryStockCountSessionRepository extends JpaRepository<InventoryStockCountSession,Long> {

    List<InventoryStockCountSession> findByRestaurantIdOrderByCreatedAtDesc(Long restaurantId);

    List<InventoryStockCountSession> findByRestaurantIdAndStatusOrderByCreatedAtDesc(
            Long restaurantId, InventoryStockCountStatus status);

    Optional<InventoryStockCountSession> findByIdAndRestaurantId(Long id, Long restaurantId);
}

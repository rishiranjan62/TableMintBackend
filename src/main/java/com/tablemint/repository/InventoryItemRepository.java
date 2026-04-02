package com.tablemint.repository;

import com.tablemint.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryItemRepository extends JpaRepository<InventoryItem,Long> {

    List<InventoryItem> findByRestaurantIdOrderByNameAsc(Long restaurantId);
    Optional<InventoryItem> findByIdAndRestaurantId(Long id, Long restaurantId);
    boolean existsByRestaurantIdAndSkuCodeIgnoreCaseAndIdNot(Long restaurantId, String skuCode, Long id);
    boolean existsByRestaurantIdAndSkuCodeIgnoreCase(Long restaurantId, String skuCode);
    Optional<InventoryItem> findByRestaurantIdAndSkuCodeIgnoreCase(Long restaurantId, String skuCode);


}

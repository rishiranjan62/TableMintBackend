package com.tablemint.repository;

import com.tablemint.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findByRestaurantIdOrderByNameAsc(Long restaurantId);

    Optional<Supplier> findByIdAndRestaurantId(Long id, Long restaurantId);

    boolean existsByRestaurantIdAndCodeIgnoreCaseAndIdNot(Long restaurantId, String code, Long id);

    boolean existsByRestaurantIdAndCodeIgnoreCase(Long restaurantId, String code);
}

package com.tablemint.repository;

import com.tablemint.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion,Long> {

    @Query("SELECT p FROM Promotion p WHERE (p.validFrom IS NULL OR p.validFrom <= :now) AND (p.validTo IS NULL OR p.validTo >= :now)")
    List<Promotion> findActiveAt(Instant now);

    List<Promotion> findByRestaurantId(Long restaurantId);

    List<Promotion> findByRestaurantIdIsNull();

    @Query("SELECT p FROM Promotion p WHERE p.restaurantId = :restaurantId AND (p.validFrom IS NULL OR p.validFrom <= :now) AND (p.validTo IS NULL OR p.validTo >= :now)")
    List<Promotion> findActiveAtAndRestaurantId(Instant now, Long restaurantId);

    @Query("SELECT p FROM Promotion p WHERE p.restaurantId IS NULL AND (p.validFrom IS NULL OR p.validFrom <= :now) AND (p.validTo IS NULL OR p.validTo >= :now)")
    List<Promotion> findActiveAtAndRestaurantIdIsNull(Instant now);
}

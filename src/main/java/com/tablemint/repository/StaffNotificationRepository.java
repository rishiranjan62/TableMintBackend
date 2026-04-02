package com.tablemint.repository;

import com.tablemint.model.StaffNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffNotificationRepository extends JpaRepository<StaffNotification, Long> {

    List<StaffNotification> findByRestaurantIdAndUserIdOrderByCreatedAtDesc(Long restaurantId, Long userId);

    Long countByRestaurantIdAndUserIdAndReadAtIsNull(Long restaurantId, Long userId);

    List<StaffNotification> findByRestaurantIdAndUserIdAndKindStartingWithOrderByCreatedAtDesc(
            Long restaurantId, Long userId, String kindPrefix);

    long countByRestaurantIdAndUserIdAndKindStartingWithAndReadAtIsNull(
            Long restaurantId, Long userId, String kindPrefix);

}

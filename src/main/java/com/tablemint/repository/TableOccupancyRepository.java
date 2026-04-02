package com.tablemint.repository;

import com.tablemint.model.TableOccupancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface TableOccupancyRepository extends JpaRepository<TableOccupancy, Long> {

    Optional<TableOccupancy> findByRestaurantTableIdAndLeftAtIsNull(Long tableId);

    /** All active occupancies at this table (for tables with multiple people). */
    List<TableOccupancy> findAllByRestaurantTableIdAndLeftAtIsNullOrderByJoinedAtAsc(Long tableId);

    Optional<TableOccupancy> findByUserIdAndLeftAtIsNull(Long userId);

    /** Restaurant id for user's active table link (no lazy access outside a transaction). */
    @Query("SELECT t.restaurantId FROM TableOccupancy o JOIN o.restaurantTable t WHERE o.user.id = :userId AND o.leftAt IS NULL")
    Optional<Long> findActiveRestaurantIdByUserId(@Param("userId") Long userId);

    /** Most recently ended occupancy for this user (for fetching last paid bill after they are unlinked). */
    Optional<TableOccupancy> findTopByUserIdAndLeftAtIsNotNullOrderByLeftAtDesc(Long userId);

    List<TableOccupancy> findByRestaurantTableIdOrderByJoinedAtDesc(Long tableId);

    /** Count of customers currently at this table (leftAt is null). Used for "group order" when > 1. */
    long countByRestaurantTableIdAndLeftAtIsNull(Long tableId);

    /** Occupancies that ended within the given range (for customer visit reports). */
    List<TableOccupancy> findByLeftAtIsNotNullAndLeftAtBetweenOrderByLeftAtDesc(Instant from, Instant to);

    List<TableOccupancy> findByLeftAtIsNotNullAndLeftAtBetweenAndRestaurantTable_RestaurantIdOrderByLeftAtDesc(
            Instant from, Instant to, Long restaurantId);

    List<TableOccupancy> findByLeftAtIsNotNullAndLeftAtBetweenAndRestaurantTable_RestaurantIdIsNullOrderByLeftAtDesc(
            Instant from, Instant to);

    void deleteByRestaurantTableId(Long tableId);
}

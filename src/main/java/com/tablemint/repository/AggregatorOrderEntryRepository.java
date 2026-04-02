package com.tablemint.repository;

import com.tablemint.model.AggregatorOrderEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface AggregatorOrderEntryRepository extends JpaRepository<AggregatorOrderEntry, Long> {

    List<AggregatorOrderEntry> findByOrderDateBetweenOrderByOrderDateDescCreatedAtDesc(
            LocalDate from, LocalDate to);

    Page<AggregatorOrderEntry> findByOrderDateBetweenOrderByOrderDateDescCreatedAtDesc(
            LocalDate from, LocalDate to, Pageable pageable);

    long countByOrderDateBetween(LocalDate from, LocalDate to);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM AggregatorOrderEntry e WHERE e.orderDate = :date")
    BigDecimal sumAmountByOrderDate(@Param("date") LocalDate date);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM AggregatorOrderEntry e WHERE e.orderDate BETWEEN :fromDate AND :toDate")
    BigDecimal sumAmountByOrderDateBetween(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

    Page<AggregatorOrderEntry> findByRestaurantIdAndOrderDateBetweenOrderByOrderDateDescCreatedAtDesc(
            Long restaurantId, LocalDate from, LocalDate to, Pageable pageable);

    List<AggregatorOrderEntry> findByRestaurantIdAndOrderDateBetweenOrderByOrderDateDescCreatedAtDesc(
            Long restaurantId, LocalDate from, LocalDate to);

    Page<AggregatorOrderEntry> findByRestaurantIdIsNullAndOrderDateBetweenOrderByOrderDateDescCreatedAtDesc(
            LocalDate from, LocalDate to, Pageable pageable);

    List<AggregatorOrderEntry> findByRestaurantIdIsNullAndOrderDateBetweenOrderByOrderDateDescCreatedAtDesc(
            LocalDate from, LocalDate to);

    long countByRestaurantIdAndOrderDateBetween(Long restaurantId, LocalDate from, LocalDate to);

    long countByRestaurantIdIsNullAndOrderDateBetween(LocalDate from, LocalDate to);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM AggregatorOrderEntry e WHERE e.restaurantId = :rid AND e.orderDate = :date")
    BigDecimal sumAmountByRestaurantIdAndOrderDate(@Param("rid") Long restaurantId, @Param("date") LocalDate date);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM AggregatorOrderEntry e WHERE e.restaurantId IS NULL AND e.orderDate = :date")
    BigDecimal sumAmountByRestaurantIdIsNullAndOrderDate(@Param("date") LocalDate date);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM AggregatorOrderEntry e WHERE e.restaurantId = :rid AND e.orderDate BETWEEN :fromDate AND :toDate")
    BigDecimal sumAmountByRestaurantIdAndOrderDateBetween(@Param("rid") Long restaurantId, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM AggregatorOrderEntry e WHERE e.restaurantId IS NULL AND e.orderDate BETWEEN :fromDate AND :toDate")
    BigDecimal sumAmountByRestaurantIdIsNullAndOrderDateBetween(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);


}

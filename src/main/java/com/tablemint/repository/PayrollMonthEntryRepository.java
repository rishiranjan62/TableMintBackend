package com.tablemint.repository;

import com.tablemint.model.PayrollMonthEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PayrollMonthEntryRepository extends JpaRepository<PayrollMonthEntry, Long> {

    Optional<PayrollMonthEntry> findByRestaurantIdAndUserIdAndPeriodYearAndPeriodMonth
            (Long restaurantId, Long userId, int periodYear, int periodMonth);

    List<PayrollMonthEntry> findByRestaurantIdAndPeriodYearAndPeriodMonthOrderByUserIdAsc(
            Long restaurantId, int periodYear, int periodMonth);

}

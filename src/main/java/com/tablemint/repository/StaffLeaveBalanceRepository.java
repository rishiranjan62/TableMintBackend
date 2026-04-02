package com.tablemint.repository;

import com.tablemint.model.StaffLeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface StaffLeaveBalanceRepository extends JpaRepository<StaffLeaveBalance, Long> {

    Optional<StaffLeaveBalance> findByRestaurantIdAndUserIdAndCycleStart(Long restaurantId, Long userId, LocalDate cycleStart);

    Optional<StaffLeaveBalance> findFirstByRestaurantIdAndUserIdAndCycleStartLessThanEqualAndCycLeEndGreaterThanEqualOrderByCycleStartDesc(
            Long restaurantId, Long userId, LocalDate date1, LocalDate date2);


}

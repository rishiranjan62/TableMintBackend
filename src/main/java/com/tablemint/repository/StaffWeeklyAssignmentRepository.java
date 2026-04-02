package com.tablemint.repository;

import com.tablemint.model.StaffWeeklyAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

public interface StaffWeeklyAssignmentRepository extends JpaRepository<StaffWeeklyAssignment, Long> {

    List<StaffWeeklyAssignment> findByRestaurantId(Long restaurantId);

    Optional<StaffWeeklyAssignment> findByRestaurantIdAndUserIdAndDayOfWeek(
            Long restaurantId, Long userId, DayOfWeek dayOfWeek);

    void deleteByRestaurantIdAndUserIdAndDayOfWeek(Long restaurantId, Long userId, DayOfWeek dayOfWeek);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM StaffWeeklyAssignment a WHERE a.restaurantId = :restaurantId AND a.workShift.id = :shiftId")
    int deleteByRestaurantIdAndShiftId(@Param("restaurantId") Long restaurantId, @Param("shiftId") Long shiftId);
}

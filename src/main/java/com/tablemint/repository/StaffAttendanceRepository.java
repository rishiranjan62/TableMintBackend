package com.tablemint.repository;

import com.tablemint.model.StaffAttendance;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StaffAttendanceRepository extends JpaRepository<StaffAttendance, Long> {

    @EntityGraph(attributePaths = {"shift"})
    Optional<StaffAttendance> findByUserIdAndRestaurantIdAndWorkDate(Long userId, Long restaurantId, LocalDate workDate);

    @EntityGraph(attributePaths = {"shift"})
    List<StaffAttendance> findByUserIdAndRestaurantIdAndWorkDateBetweenOrderByWorkDateDesc(
            Long userId, Long restaurantId, LocalDate from, LocalDate to);

    List<StaffAttendance> findByUserIdAndRestaurantIdAndClockOutAtIsNullAndWorkDateLessThan(
            Long userId, Long restaurantId, LocalDate beforeDate);

    @Query("""
            SELECT DISTINCT a FROM StaffAttendance a
            LEFT JOIN FETCH a.shift
            WHERE a.restaurantId = :restaurantId
            AND a.workDate BETWEEN :from AND :to
            AND (:userId IS NULL OR a.userId = :userId)
            ORDER BY a.workDate DESC, a.clockInAt ASC
            """)
    List<StaffAttendance> findTeamLog(
            @Param("restaurantId") Long restaurantId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to,
            @Param("userId") Long userId);

    @Query("""
            SELECT a FROM StaffAttendance a
            LEFT JOIN FETCH a.shift
            WHERE a.id = :id
            """)
    Optional<StaffAttendance> findByIdWithShift(@Param("id") Long id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE StaffAttendance a SET a.shift = NULL WHERE a.restaurantId = :restaurantId AND a.shift.id = :shiftId")
    int clearShiftByRestaurantAndShiftId(@Param("restaurantId") Long restaurantId, @Param("shiftId") Long shiftId);
}

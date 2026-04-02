package com.tablemint.repository;

import com.tablemint.model.LeaveRequest;
import com.tablemint.model.LeaveRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Long> {

    List<LeaveRequest> findByRestaurantIdAndUserIdOrderByCreatedAtDesc(Long restaurantId, Long userId);
    List<LeaveRequest> findByRestaurantIdAndStatusOrderByCreatedAtAsc(Long restaurantId, LeaveRequestStatus status);

    @Query("""
        SELECT COUNT(r) FROM LeaveRequest r
        WHERE r.restaurantId = :restaurantId AND r.userId = :userId
        AND r.status IN :activeStatuses
        AND r.startDate <= :end AND r.endDate >= :start
        AND (:excludeId IS NULL OR r.id <> :excludeId)
        """)
    long countOverlapping(
            @Param("restaurantId") Long restaurantId,
            @Param("userId") Long userId,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end,
            @Param("excludeId") Long excludeId,
            @Param("activeStatuses") Collection<LeaveRequestStatus> activeStatuses);

    /** Pending + approved leave overlapping the window (for admin/manager roster planning). */
    @Query(
            """
            SELECT r FROM LeaveRequest r
            WHERE r.restaurantId = :restaurantId
            AND r.status IN :statuses
            AND r.endDate >= :fromDate
            AND (:toDate IS NULL OR r.startDate <= :toDate)
            ORDER BY r.startDate ASC, r.endDate ASC, r.id ASC
            """)
    List<LeaveRequest> findTeamSchedule(
            @Param("restaurantId") Long restaurantId,
            @Param("statuses") Collection<LeaveRequestStatus> statuses,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate);

}

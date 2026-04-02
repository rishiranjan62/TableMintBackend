package com.tablemint.repository;

import com.tablemint.model.StaffTask;
import com.tablemint.model.StaffTaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface StaffTaskRepository extends JpaRepository<StaffTask, Long> {

    List<StaffTask> findByRestaurantIdOrderByCreatedAtDesc(Long restaurantId);

    List<StaffTask> findByRestaurantIdAndAssigneeUserIdOrderByCreatedAtDesc(Long restaurantId, Long assigneeUserId);

    @Query("SELECT COUNT(t) FROM StaffTask t WHERE t.restaurantId = :rid AND t.assigneeUserId = :uid "
            + "AND t.status = :st AND t.updatedAt >= :from AND t.updatedAt < :to")
    long countCompletedInRange(
            @Param("rid") Long restaurantId,
            @Param("uid") Long assigneeUserId,
            @Param("st") StaffTaskStatus status,
            @Param("from") Instant from,
            @Param("to") Instant to);
}

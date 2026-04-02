package com.tablemint.repository;

import com.tablemint.model.AuditAction;
import com.tablemint.model.AuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    @Query("SELECT a FROM AuditLog a WHERE (:from IS NULL OR a.createdAt >= :from) AND (:to IS NULL OR a.createdAt <= :to) "
            + "AND (:userId IS NULL OR a.userId = :userId) AND (:action IS NULL OR a.action = :action) "
            + "AND (:restaurantId IS NULL OR a.restaurantId = :restaurantId) "
            + "ORDER BY a.createdAt DESC")
    List<AuditLog> findFiltered(
            @Param("from") Instant from,
            @Param("to") Instant to,
            @Param("userId") Long userId,
            @Param("action") AuditAction action,
            @Param("restaurantId") Long restaurantId,
            Pageable pageable);

    @Query("SELECT COUNT(a) FROM AuditLog a WHERE (:from IS NULL OR a.createdAt >= :from) AND (:to IS NULL OR a.createdAt <= :to) "
            + "AND (:userId IS NULL OR a.userId = :userId) AND (:action IS NULL OR a.action = :action) "
            + "AND (:restaurantId IS NULL OR a.restaurantId = :restaurantId)")
    long countFiltered(
            @Param("from") Instant from,
            @Param("to") Instant to,
            @Param("userId") Long userId,
            @Param("action") AuditAction action,
            @Param("restaurantId") Long restaurantId);


}

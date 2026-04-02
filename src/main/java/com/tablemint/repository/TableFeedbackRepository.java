package com.tablemint.repository;

import com.tablemint.model.TableFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface TableFeedbackRepository extends JpaRepository<TableFeedback, Long> {

    List<TableFeedback> findByRestaurantTableIdOrderByCreatedAtDesc(Long tableId);

    List<TableFeedback> findByCreatedAtBetweenOrderByCreatedAtDesc(Instant from, Instant to);

    @Query("SELECT f FROM TableFeedback f JOIN f.restaurantTable t WHERE t.restaurantId = :rid AND f.createdAt >= :from AND f.createdAt <= :to ORDER BY f.createdAt DESC")
    List<TableFeedback> findByRestaurantIdAndCreatedAtBetween(@Param("rid") Long restaurantId, @Param("from") Instant from, @Param("to") Instant to);


    void deleteByRestaurantTableId(Long tableId);
}

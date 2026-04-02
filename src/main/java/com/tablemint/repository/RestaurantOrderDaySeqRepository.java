package com.tablemint.repository;

import com.tablemint.model.RestaurantOrderDaySeq;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface RestaurantOrderDaySeqRepository extends JpaRepository<RestaurantOrderDaySeq, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM RestaurantOrderDaySeq s WHERE s.restaurantId=:rid AND s.orderDay=:day")
    Optional<RestaurantOrderDaySeq> findForUpdate(@Param("rid") Long restaurantId, @Param("day") LocalDate orderDay);
}

package com.tablemint.repository;

import com.tablemint.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    Optional<Receipt> findTopByTableIdOrderByGeneratedAtDesc(Long tableId);

    Optional<Receipt> findTopByTableIdAndForCustomerUserIdOrderByGeneratedAtDesc(Long tableId, Long forCustomerUserId);

    /**
     * Latest receipt for this table whose payment time falls in [start, end] (inclusive).
     */
    Optional<Receipt> findTopByTableIdAndGeneratedAtBetweenOrderByGeneratedAtDesc(Long tableId, Instant startInclusive, Instant endInclusive);

    Optional<Receipt> findTopByTableIdAndForCustomerUserIdAndGeneratedAtBetweenOrderByGeneratedAtDesc(
            Long tableId, Long forCustomerUserId, Instant startInclusive, Instant endInclusive);

    Optional<Receipt> findTopByTableIdAndForCustomerUserIdIsNullAndGeneratedAtBetweenOrderByGeneratedAtDesc(
            Long tableId, Instant startInclusive, Instant endInclusive);

}

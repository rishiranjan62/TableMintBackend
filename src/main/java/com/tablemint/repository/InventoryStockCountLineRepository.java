package com.tablemint.repository;

import com.tablemint.model.InventoryStockCountLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryStockCountLineRepository extends JpaRepository<InventoryStockCountLine, Long> {

    List<InventoryStockCountLine> findBySessionIdOrderByIdAsc(Long sessionId);

    Optional<InventoryStockCountLine> findBySessionIdAndInventoryItemId(Long sessionId, Long inventoryItemId);

    void deleteBySessionId(Long sessionId);

}

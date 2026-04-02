package com.tablemint.repository;

import com.tablemint.model.PurchaseOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.sound.sampled.Line;
import java.util.List;
import java.util.Optional;

public interface PurchaseOrderLineRepository extends JpaRepository<PurchaseOrderLine, Line> {

    List<PurchaseOrderLine> findByPurchaseOrderId(Long purchaseOrderId);

    Optional<PurchaseOrderLine> findByIdAndPurchaseOrderId(Long id, Long purchaseOrderId);
}

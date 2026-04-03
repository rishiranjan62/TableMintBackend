package com.tablemint.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "inventory_stock_adjustment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryStockAdjustment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Enumerated(EnumType.STRING)
    @Column(name = "adjustment_type", nullable = false, length = 32)
    private InventoryAdjustmentType adjustmentType;

    @Column(nullable = false, precision = 18, scale = 4)
    private BigDecimal delta;

    @Column(name = "quantity_after", nullable = false, precision = 18, scale = 4)
    private BigDecimal quantityAfter;

    @Column(length = 512)
    private String reason;

    @Column(name = "purchase_order_line_id")
    private Long purchaseOrderLineId;

    @Column(name = "lot_number", length = 128)
    private String lotNumber;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    /** Unit cost for this purchase receipt row (standalone or PO); used for spend analytics. */
    @Column(name = "receipt_unit_cost", precision = 18, scale = 6)
    private BigDecimal receiptUnitCost;

    @Column(name = "supplier_invoice_ref", length = 256)
    private String supplierInvoiceRef;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "stock_count_session_id")
    private Long stockCountSessionId;

    @Column(name = "created_by_user_id", nullable = false)
    private Long createdByUserId;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @PrePersist
    void prePersist() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
        if (adjustmentType == null) {
            adjustmentType = InventoryAdjustmentType.LEGACY;
        }
    }

}

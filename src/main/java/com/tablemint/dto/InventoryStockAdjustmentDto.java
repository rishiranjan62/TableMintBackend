package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
public class InventoryStockAdjustmentDto {

    private Long id;
    private Long itemId;
    private String itemName;
    private String quantityUnit;
    private String adjustmentType;
    private BigDecimal delta;
    private BigDecimal quantityAfter;
    private String reason;
    private Long purchaseOrderLineId;
    private String lotNumber;
    private LocalDate expiryDate;
    private String supplierInvoiceRef;
    private Long orderId;
    private Long stockCountSessionId;
    private Long createdByUserId;
    private Instant createdAt;

}

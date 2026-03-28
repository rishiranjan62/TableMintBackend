package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PurchaseOrderLineDto {
    private Long id;
    private Long inventoryItemId;
    private String itemName;
    private String skuCode;
    private BigDecimal quantityOrdered;
    private BigDecimal quantityReceived;
    private BigDecimal unitCost;
    private BigDecimal remainingToReceive;
}

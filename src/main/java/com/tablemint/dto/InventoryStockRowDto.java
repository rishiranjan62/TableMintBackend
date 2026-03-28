package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class InventoryStockRowDto {

    private Long itemId;
    private String itemName;
    private String skuCode;
    private String description;
    private BigDecimal reorderPoint;
    private BigDecimal parLevel;
    private BigDecimal quantityOnHand;

    private String quantityUnit;
    private boolean shortShelfLife;
    /** True when reorderPoint set and on-hand is at or below it.*/
    private boolean lowStock;
}

package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class InventoryItemDto {

    private Long id;
    private String skuCode;
    private String name;
    private String description;
    private BigDecimal reorderPoint;
    private BigDecimal parLevel;
    private BigDecimal quantityOnHand;
    private BigDecimal averageUnitCost;
    private BigDecimal lastPurchaseUnitCost;
    private boolean shortShelfLife;
    /**
     * Optional unit label for quantities (kg,g,L).
     */
    private String quantityUnit;
    /**
     * Optional reporting category for spend dashboards.
     */
    private String inventoryCategory;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;

}

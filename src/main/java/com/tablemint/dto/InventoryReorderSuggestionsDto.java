package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class InventoryReorderSuggestionsDto {
    private Long itemId;
    private String itemName;
    private String skuCode;
    private String quantityUnit;
    private BigDecimal quantityOnHand;
    private BigDecimal parLevel;
    private BigDecimal reorderPoint;
    private BigDecimal suggestedOrderQuantity;
    private BigDecimal estimatedUnitCost;
}

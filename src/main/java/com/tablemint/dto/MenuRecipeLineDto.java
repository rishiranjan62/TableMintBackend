package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MenuRecipeLineDto {

    private Long id;
    private Long inventoryItemId;
    private String inventoryItemName;
    private BigDecimal quantityPerPortion;
    /** When set, quantityPerportion is in this unit and is converted to stock for deductions. */
    private String portionQuantityUnit;
    private BigDecimal yieldLossPercent;
}

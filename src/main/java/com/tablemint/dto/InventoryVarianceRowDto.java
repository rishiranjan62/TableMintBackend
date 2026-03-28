package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class InventoryVarianceRowDto {
    private Long itemId;
    private String itemName;
    private String quantityUnit;
    /**
     * From recipes × sold aty in range.
     */
    private BigDecimal theoreticalUsage;
    /**
     * DAILY_USAGE + RECIPE_CONSUMPTION adjustments in range.
     */
    private BigDecimal actualUsage;
    /**
     * actual - theoretical (positive = used more than recipe predicts).
     */
    private BigDecimal variance;
}

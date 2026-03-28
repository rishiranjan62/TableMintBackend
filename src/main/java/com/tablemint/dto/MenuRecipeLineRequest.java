package com.tablemint.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuRecipeLineRequest {

    @NotNull
    private Long inventoryItemId;

    @NotNull
    @Positive
    private BigDecimal quantityPerPortion;

    /**
     * unit of quantityPerPortion when not the same as inventory stock unit (e.g. "g" with stock in Kg). Omit or null = same as stock
     */
    private String portionQuantityUnit;
    /**
     * 0= none: applied as multiplier(1+pct/100).
     */
    private BigDecimal yieldLossPercent;


}

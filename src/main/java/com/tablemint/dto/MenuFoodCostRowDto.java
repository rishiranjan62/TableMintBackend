package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MenuFoodCostRowDto {
    private Long menuItemId;
    private String menuItemName;
    private String category;
    private BigDecimal menuPrice;
    private boolean hasRecipe;
    /**
     * Estimated ingredient cost per portion from BOM × average unit costs.
     */
    private BigDecimal recipeFoodCost;
    /**
     * recipeFoodCost / menuPrice × 100 when both known.
     */
    private BigDecimal foodCostPercent;
    /**
     * Max sellable portions from current stock (null if no recipe or unconstrained).
     */
    private Integer maxPortionsFromStock;
    private String LimitingIngredientName;
    private boolean missingIngredientUnitCost;
}

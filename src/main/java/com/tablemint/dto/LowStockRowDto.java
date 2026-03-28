package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LowStockRowDto {

    private Long itemId;
    private String itemName;
    private String skuCode;
    private BigDecimal quantityOnHand;
    private BigDecimal reorderPoint;
    private BigDecimal parLevel;
    private String quantityUnit;
    /**
     * reorder - on hand when reorder set; else par - on hand; negative = how far below target.
     */
    private BigDecimal shortfall;
}

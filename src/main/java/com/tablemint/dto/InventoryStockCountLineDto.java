package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class InventoryStockCountLineDto {

    private Long lineId;
    private Long itemId;
    private String itemName;
    private String skuCode;
    private String quantityUnit;
    /** Current system on-hand (when session was loaded).  */
    private BigDecimal systemOnHand;
    private BigDecimal countedQuantity;
}

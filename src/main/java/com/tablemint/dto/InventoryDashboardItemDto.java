package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class InventoryDashboardItemDto {
    private Long itemId;
    private String itemName;
    private String quantityUnit;
    private BigDecimal dailyUsage;
    private BigDecimal wastage;
    private BigDecimal stockIn;
}

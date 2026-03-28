package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class InventoryCategorySpendRowDto {
    private String category;
    private BigDecimal spendAmount;
}

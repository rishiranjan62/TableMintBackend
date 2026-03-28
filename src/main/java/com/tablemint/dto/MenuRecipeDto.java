package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class MenuRecipeDto {
    private Long id;
    private Long menuItemId;
    private String menuItemName;
    private BigDecimal yieldLossPercent;
    private String notes;
    private List<MenuRecipeLineDto> lines;
}

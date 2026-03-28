package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TopItemEntry {
    private Long menuItemId;
    private String menuItemName;
    private int quantitySold;
    private BigDecimal revenue;
}

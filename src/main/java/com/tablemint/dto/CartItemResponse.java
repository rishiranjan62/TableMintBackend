package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CartItemResponse {

    private Long id;
    private Long menuItemId;
    private String menuItemName;
    private BigDecimal unitPrice;
    private int quantity;
    private BigDecimal lineTotal;
    private String notes;
}

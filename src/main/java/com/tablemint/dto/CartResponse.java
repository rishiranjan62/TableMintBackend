package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CartResponse {

    private Long cartId;
    private Long tableId;
    private String tableNumber;
    private List<CartItemResponse> items;
    private BigDecimal totalAmount;
    private int totalItems;
}

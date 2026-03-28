package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class SalesDetailEntry {
    private Long orderId;
    /**
     * Staff/customer-facing numeric ticket (per restaurant, daily sequence).
     */
    private String displayOrderNumber;
    private Long tableId;
    private String tableNumber;
    private Instant createdAt;
    private BigDecimal totalAmount;
    private int itemCount;
    private List<OrderItemResponse> items;
}

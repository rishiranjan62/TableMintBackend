package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class OrderItemResponse {

    private Long id;
    private Long menuItemId;
    private String menuItemName;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal LineTotal;
    private String LineNotes;
    private Long assignedChefUserId;
    private String assignedChefName;
    private Instant preparedAt;
    private Instant prepStartedAt;
    private Instant servedAt;
}

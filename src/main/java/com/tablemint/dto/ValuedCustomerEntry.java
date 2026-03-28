package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ValuedCustomerEntry {
    private Long userId;
    private String phoneNumber;
    private String name;
    private int paidOrderCount;
    private BigDecimal totalSpent;
    private BigDecimal avgOrderValue;
}

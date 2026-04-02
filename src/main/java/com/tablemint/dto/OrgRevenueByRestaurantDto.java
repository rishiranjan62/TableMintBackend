package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrgRevenueByRestaurantDto {
    private Long restaurantId;
    private String restaurantName;
    private BigDecimal paidRevenue;

}

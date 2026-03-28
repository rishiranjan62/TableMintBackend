package com.tablemint.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformAverageTableValueDto {

    private Long restaurantId;
    private String restaurantName;
    private BigDecimal totalRevenue;
    private int tableCount;
    private BigDecimal averageTableValue;
}

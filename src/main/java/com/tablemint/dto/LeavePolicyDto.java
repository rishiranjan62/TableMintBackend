package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LeavePolicyDto {

    private Long restaurantId;
    private BigDecimal daysPerYear;
    private Integer renewMonth;
    private Integer renewDay;
    private Integer plannedLeaveMinDays;
    private Integer autoApproveMaxDays;
}

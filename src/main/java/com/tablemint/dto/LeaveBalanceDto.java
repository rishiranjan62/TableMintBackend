package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class LeaveBalanceDto {

    private LocalDate cycleStart;
    private LocalDate cycleEnd;
    private BigDecimal daysAlLocated;
    private BigDecimal daysUsed;
    private BigDecimal daysPending;
    private BigDecimal remaining;
}

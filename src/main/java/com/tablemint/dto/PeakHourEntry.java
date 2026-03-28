package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PeakHourEntry {
    /** 0-23 in the requested timezone*/

    private int hour;
    private long orderCount;
    private BigDecimal revenue;
}

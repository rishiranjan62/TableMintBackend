package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TurnOutByDayEntry {
    private LocalDate date;
    /**
     * Table session ended(customer left table) that day
     */
    private long visitCount;
}

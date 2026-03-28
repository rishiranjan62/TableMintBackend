package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PayrollMonthReportDto {

    private int year;
    private int month;
    private int standardMonthHours;
    private List<PayrollMonthRowDto> rows;
}

package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class TheoreticalUsageReportDto {

    private LocalDate from;
    private LocalDate to;
    private String timeZone;
    private String basis;
    private List<TheoreticalUsageRowDto> rows;
}

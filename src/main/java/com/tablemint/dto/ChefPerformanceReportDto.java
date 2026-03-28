package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class ChefPerformanceReportDto {
    private Instant from ;
    private Instant to;
    private List<ChefPerformanceSummaryDto> summary;
    private List<ChefPreparedLineDto> details;
}

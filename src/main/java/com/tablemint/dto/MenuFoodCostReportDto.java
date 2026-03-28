package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MenuFoodCostReportDto {
    private String basis;
    private List<MenuFoodCostRowDto> rows;
}

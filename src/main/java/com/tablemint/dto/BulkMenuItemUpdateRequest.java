package com.tablemint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BulkMenuItemUpdateRequest {

    private List<Long> ids;
    private BigDecimal price;
    private Boolean available;
    private String category;

}

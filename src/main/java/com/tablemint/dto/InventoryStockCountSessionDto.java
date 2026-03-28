package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class InventoryStockCountSessionDto {
    private Long id;
    private Long createdByUserId;
    private String status;
    private String note;
    private Instant createdAt;

    private Instant postedAt;
    private Long postedByUserId;
    private List<InventoryStockCountLineDto> lines;
}

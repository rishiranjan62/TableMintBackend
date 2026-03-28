package com.tablemint.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UpsertStockCountLinesRequest {

    @Valid
    @NotNull
    private List<StockCountLineInput> Lines;

    @Data
    public static class StockCountLineInput {
        @NotNull
        private Long itemId;
        private BigDecimal countedQuantity;
    }
}

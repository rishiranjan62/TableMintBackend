package com.tablemint.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatePurchaseOrderLineRequest {

    @NotNull
    private Long inventoryItemId;

    @NotNull
    @Positive
    private BigDecimal quantityOrdered;

    /** If omitted, suppler default unit cost is used when available.*/
    private BigDecimal unitCost;

}

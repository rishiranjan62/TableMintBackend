package com.tablemint.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReceivePurchaseOrderLineRequest {

    @NotNull
    @Positive
    private BigDecimal quantity;

    /** Defaults to the line's ordered unit cost if omitted*/
    private BigDecimal unitCost;
    @Size(max = 128)
    private String lotNumber;
    private LocalDate expiryDate;

    @Size(max = 128)
    private String invoiceNumber;

    private LocalDate invoiceDate;
}

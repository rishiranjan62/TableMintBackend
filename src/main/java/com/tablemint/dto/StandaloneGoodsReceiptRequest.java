package com.tablemint.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class StandaloneGoodsReceiptRequest {

    private Long itemId;
    @Size(max = 64)
    private String skuCode;
    @Positive
    private BigDecimal quantity;
    @NotNull
    @Positive
    private BigDecimal unitCost;
    private Long supplierId;
    @Size(max = 128)
    private String LotNumber;
    private LocalDate expiryDate;
    @Size(max = 512)
    private String note;
}

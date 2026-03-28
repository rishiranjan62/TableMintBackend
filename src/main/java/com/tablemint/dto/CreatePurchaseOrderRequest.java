package com.tablemint.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.time.LocalDate;
import java.util.List;

@Data
public class CreatePurchaseOrderRequest {

    @NotNull
    private Long supplierId;

    private LocalDate expectedDeliveryDate;

    @Size(max = 2000)
    private String notes;

    @NotEmpty
    @Valid
    private List<CreatePurchaseOrderLineRequest> lines;
}

package com.tablemint.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateSupplierRequest {

    @Size(max = 64)
    private String code;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String contactName;

    @Size(max = 64)
    private String phone;

    @Size(max = 255)
    private String email;

    private Integer leadTimeDays;

    private BigDecimal defaultUnitCost;

    @Size(max = 2000)
    private String notes;

}

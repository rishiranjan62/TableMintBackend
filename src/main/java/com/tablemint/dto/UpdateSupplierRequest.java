package com.tablemint.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateSupplierRequest {

    @Size(max = 64)
    private String code;
    @Size(max = 255)
    private String name;
    @Size(max = 255)
    private String contactName;
    @Size(max = 64)
    private String phone;
    @Size(max = 255)
    private String email;
    private Integer LeadTimeDays;
    private BigDecimal defaultUnitCost;
    @Size(max = 2000)
    private String notes;
    private Boolean active;
}

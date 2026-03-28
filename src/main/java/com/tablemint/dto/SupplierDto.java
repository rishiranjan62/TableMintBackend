package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class SupplierDto {
    private Long id;
    private String code;
    private String name;
    private String contactName;
    private String phone;
    private String email;
    private Integer LeadTimeDays;
    private BigDecimal defaultUnitCost;
    private String notes;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
}

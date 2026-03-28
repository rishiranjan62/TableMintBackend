package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class TableCustomerInfo {

    private Long userId;
    private String phoneNumber;
    private String name;
    private Instant joinedAt;
}

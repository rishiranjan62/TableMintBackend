package com.tablemint.dto;

import com.tablemint.model.CustomerRequestStatus;
import com.tablemint.model.CustomerRequestType;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class CustomerRequestResponse {

    private Long id;
    private Long tableId;
    private String tableNumber;
    private CustomerRequestType type;
    private CustomerRequestStatus status;
    private Instant createdAt;
    private Instant resolvedAt;

}

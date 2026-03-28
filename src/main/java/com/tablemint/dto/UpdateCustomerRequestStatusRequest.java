package com.tablemint.dto;

import com.tablemint.model.CustomerRequestStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCustomerRequestStatusRequest {
    @NotNull(message = "Status is required")
    private CustomerRequestStatus status;
}

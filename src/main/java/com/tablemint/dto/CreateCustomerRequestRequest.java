package com.tablemint.dto;

import com.tablemint.model.CustomerRequestType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCustomerRequestRequest {

    @NotNull(message = "Request type is required")
    private CustomerRequestType type;
}

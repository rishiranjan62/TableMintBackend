package com.tablemint.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class ClaimMyOrderItemsRequest {

    @NotEmpty
    private List<Long> orderItemIds;
}

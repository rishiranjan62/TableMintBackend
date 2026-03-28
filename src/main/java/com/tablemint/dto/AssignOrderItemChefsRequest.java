package com.tablemint.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class AssignOrderItemChefsRequest {

    @NotEmpty
    @Valid
    private List<OrderItemChefAssignmentDto> assignments;
}

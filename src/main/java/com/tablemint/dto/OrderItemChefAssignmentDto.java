package com.tablemint.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemChefAssignmentDto {

    @NotNull
    private Long orderItemId;

    /**
     * Null clears line assignment
     */
    private Long chefUserId;

}

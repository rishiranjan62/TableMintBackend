package com.tablemint.dto;

import com.tablemint.model.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusRequest {
    @NotNull(message = "status is required")
    private OrderStatus status;

    /**
     * Optional: estimated minutes until order is ready
     */
    private Integer estimatedMinutes;
}

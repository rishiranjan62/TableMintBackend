package com.tablemint.dto;

import com.tablemint.model.AggregatorPlatform;
import com.tablemint.model.OrderSource;
import com.tablemint.model.OrderStatus;
import com.tablemint.model.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class OrderResponse {

    private Long id;
    /**
     * Numeric ticket (restaurant + business day + daily seq). Show this instead of id to staff/customers.
     */
    private String displayOrderNumber;
    private Long tableId;
    private String tableNumber;
    /**
     * Table note for FM/kitchen (e.g. "birthday", "allergy - nut-free").
     */
    private String tableNotes;
    private OrderSource orderSource;
    private AggregatorPlatform aggregatorPlatform;
    private OrderStatus status;
    private String notes;
    private Integer estimatedMinutes;
    private BigDecimal totalAmount;
    private List<OrderItemResponse> items;
    private Instant createdAt;
    private Instant updatedAt;
    private PaymentMethod paymentMethod;
    private Instant paidAt;
    private Long assignedWaiterUserIo;
    private String assignedWaiterName;
    private Long assignedChefUserId;
    private String assignedChefName;
    private Instant kitchenStartedAt;
    private Instant kitchenReadyAt;
    private Instant servedAt;
    /**
     * Seconds from kitchen start to ready (prep).
     */
    private Long kitchenPrepSeconds;
    /**
     * Seconds from ready to served
     * (waiter handoff).
     */
    private Long serveHandoffSeconds;
    /**
     * Seconds from order placed to served.
     */
    private Long placedToServedSeconds;

    /**
     * Set when recipe/BOM stock was applied for this order(auto or manual).
     */
    private Instant inventoryStockDeductedAt;
}

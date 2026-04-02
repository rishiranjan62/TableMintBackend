package com.tablemint.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "table_id", nullable = false)
    private RestaurantTable restaurantTable;

    /** Denormalized from table for sequencing; may be null only on legacy rows. */
    @Column(name = "restaurant_id")
    private Long restaurantId;

    /** Calendar day (Asia/Kolkata) used for daily ticket sequence. */
    @Column(name = "order_business_day")
    private LocalDate orderBusinessDay;

    /**
     * Human-facing numeric ticket: 6-digit restaurant + yyyyMMdd + 7-digit daily sequence.
     * Unique globally; internal {@link #id} remains the API primary key.
     */
    @Column(name = "display_order_number", nullable = false, length = 24)
    private String displayOrderNumber;

    /** Customer who placed this order (for "My Orders"). Null when placed by staff or before this field existed. */
    @Column(name = "user_id")
    private Long userId;

    /** QR = customer at table via app; DINE_IN = staff/walk-in; AGGREGATOR = manual entry (Zomato/Swiggy etc.). */
    @Enumerated(EnumType.STRING)
    @Column(name = "order_source", length = 20)
    @Builder.Default
    private OrderSource orderSource = OrderSource.DINE_IN;

    /** Platform for aggregator orders (Zomato, Swiggy, Other). Null for non-aggregator orders. */
    @Enumerated(EnumType.STRING)
    @Column(name = "aggregator_platform", length = 20)
    private AggregatorPlatform aggregatorPlatform;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private OrderStatus status = OrderStatus.PENDING;

    @Column(length = 500)
    private String notes;

    /** Estimated minutes until order is ready (e.g. 15). Set by staff when updating status. */
    @Column(name = "estimated_minutes")
    private Integer estimatedMinutes;

    @Column(nullable = false, precision = 12, scale = 2)
    @Builder.Default
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", length = 20)
    private PaymentMethod paymentMethod;

    private Instant paidAt;

    @Column(name = "assigned_waiter_user_id")
    private Long assignedWaiterUserId;

    @Column(name = "assigned_chef_user_id")
    private Long assignedChefUserId;

    /** First transition to PREPARING (kitchen work started). */
    @Column(name = "kitchen_started_at")
    private Instant kitchenStartedAt;

    /** Transition to READY (kitchen handoff). */
    @Column(name = "kitchen_ready_at")
    private Instant kitchenReadyAt;

    /** Transition to SERVED (waiter delivered to table). */
    @Column(name = "served_at")
    private Instant servedAt;

    /** When recipe-based stock deduction was applied for this order (idempotent). */
    @Column(name = "inventory_stock_deducted_at")
    private Instant inventoryStockDeductedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();

    @PrePersist
    void prePersist() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = Instant.now();
    }


}

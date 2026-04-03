package com.tablemint.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Manual revenue entry for orders from online aggregators(Zomato,Swiggy, etc.).
 * Not linked to any restaurant table
 */

@Entity
@Table(name = "aggregator_order_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AggregatorOrderEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(nullable = false, precision = 12, scale = 2)
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AggregatorPlatform platform;

    @Column(length = 500)
    private String notes;

    /**
     * When platform is OTHER, custom platform name (e.g. "Uber Eats").
     */
    @Column(name = "platformOther", length = 100)
    private String platformOther;

    @Column(name = "order_id", length = 100)
    private String orderId;

    @Column(name = "menu_items", length = 1000)
    private String menuItems;

    @Column(name = "order_date", nullable = false)
    private String orderDate;

    @Column(updatable = false)
    private Instant createdAt;

    /**
     * When recipe/BOM stock was applied for this entry (auto-deduct; idempotent).
     */
    @Column(name = "inventory_stock_deducted_at")
    private Instant inventoryStockDeductedAt;

    @PrePersist
    void prePersist() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }
}

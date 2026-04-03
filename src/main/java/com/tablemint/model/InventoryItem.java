package com.tablemint.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "inventory_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    @Column(name = "sku_code", length = 64)
    private String skuCode;

    @Column(nullable = false, length = 512)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(name = "reorder_point", precision = 18, scale = 4)
    private BigDecimal reorderPoint;

    @Column(name = "par_level", precision = 18, scale = 4)
    private BigDecimal parLevel;

    @Column(name = "quantity_on_hand", nullable = false, precision = 18, scale = 4)
    private BigDecimal quantityOnHand;

    @Column(name = "short_shelf_life", nullable = false)
    private boolean shortShelfLife;

    /**
     * Optional label for quantities (e.g. kg, g, L, mL, pcs).
     */
    @Column(name = "quantity_unit", length = 32)
    private String quantityUnit;

    /**
     * Optional spend / reporting bucket (e.g. Produce, Dairy, Dry goods).
     */
    @Column(name = "inventory_category", length = 80)
    private String inventoryCategory;

    /**
     * Weighted average unit cost from purchase receipts (optional).
     */
    @Column(name = "average_unit_cost", precision = 18, scale = 6)
    private BigDecimal averageUnitCost;

    /**
     * Unit cost from the most recent purchase receipt.
     */
    @Column(name = "last_purchase_unit_cost", precision = 18, scale = 6)
    private BigDecimal lastPurchaseUnitCost;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    void prePersist() {
        Instant n = Instant.now();
        if (createdAt == null) {
            createdAt = n;
        }
        updatedAt = n;
        if (quantityOnHand == null) {
            quantityOnHand = BigDecimal.ZERO;
        }
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = Instant.now();
    }


}

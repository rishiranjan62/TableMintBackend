package com.tablemint.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "restaurant_tables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Tenant: which restaurant this table belongs to. Null = legacy (single-tenant) data. */
    @Column(name = "restaurant_id")
    private Long restaurantId;

    /**
     * Display / business id within the restaurant: 1, 2, 3… (not the global PK).
     * Global {@link #id} is still used for API paths and FKs.
     */
    @Column(name = "restaurant_sequence")
    private Integer restaurantSequence;

    @Column(nullable = false, length = 20)
    private String tableNumber;

    @Column(nullable = false, length = 100)
    private String qrCodeId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private TableStatus status = TableStatus.AVAILABLE;

    /** Short note for FM/kitchen (e.g. "birthday", "allergy – nut-free"). */
    @Column(length = 500)
    private String notes;

    /** Optional message shown to customer when table is marked available (e.g. "Thanks for dining!"). Cleared when table becomes OCCUPIED. */
    @Column(name = "last_customer_message", length = 255)
    private String lastCustomerMessage;

    /** When true, service charge is waived for this table's current bill (e.g. customer requested). Set by admin on billing page; cleared after pay or when table becomes AVAILABLE. Null treated as false. */
    @Column(name = "service_charge_waived")
    @Builder.Default
    private Boolean serviceChargeWaived = false;

    /**
     * When true (and tenant has table-sharing value-added), each customer at this table uses a separate cart and bill.
     * Cleared when the table returns to AVAILABLE.
     */
    @Column(name = "table_sharing_enabled", nullable = false)
    @Builder.Default
    private Boolean tableSharingEnabled = false;

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

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

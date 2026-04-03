package com.tablemint.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(length = 200)
    private String lineNotes;

    /**
     * Kitchen manager assigns line to this chef; used for split tickets and performance.
     */
    @Column(name = "assigned_chef_user_id")
    private Long assignedChefUserId;

    /**
     * Set when the assigned chef (or kitchen lead) marks this line prepared.
     */
    @Column(name = "prepared_at")
    private Instant preparedAt;

    /**
     * Split tickets: set when this line's assigned chef begins work (or when a non-split ticket goes to PREPARING).
     * Other chefs' lines are not updated until they start their own lines.
     */
    @Column(name = "prep_started_at")
    private Instant prepStartedAt;

    /**
     * Set when floor staff marks this line delivered to the table (after kitchen prepared it).
     */
    @Column(name = "served_at")
    private Instant servedAt;

    @Column(updatable = false)
    private Instant createdAt;

    @PrePersist
    void prePersist() {
        createdAt = Instant.now();
    }
}

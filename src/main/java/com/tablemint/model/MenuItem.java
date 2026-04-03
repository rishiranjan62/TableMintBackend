package com.tablemint.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "menu_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Tenant: which restaurant this item belongs to. Null = legacy (single-tenant) data. */
    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(length = 50)
    private String category;

    @Column(name = "image_url", length = 2048)
    private String imageUrl;

    @Column(nullable = false)
    @Builder.Default
    private boolean available = true;

    /** Staff-only note when 86 / unavailable (e.g. "Out of salmon"). Not shown on public menu list. */
    @Column(name = "unavailable_note", length = 255)
    private String unavailableNote;

    @Column(nullable = false)
    @Builder.Default
    private boolean vegetarian = false;

    @Column(name = "gluten_free", nullable = false)
    @Builder.Default
    private boolean glutenFree = false;

    @Column(length = 255)
    private String allergens;

    /** Soft delete: when true, item is hidden from lists and treated as not found. Order history still references it. */
    @Column(nullable = false)
    @Builder.Default
    private boolean deleted = false;

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

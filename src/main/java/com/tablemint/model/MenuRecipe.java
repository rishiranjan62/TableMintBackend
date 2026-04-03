package com.tablemint.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu_recipe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    @Column(name = "menu_item_id", nullable = false)
    private Long menuItemId;

    @Column(length = 2000)
    private String notes;

    /**
     * Extra yield loss applied to all lines (e.g. prep waste), 0–100+.
     */
    @Column(name = "yield_loss_percent", nullable = false, precision = 8, scale = 4)
    @Builder.Default
    private BigDecimal yieldLossPercent = BigDecimal.ZERO;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MenuRecipeLine> lines = new ArrayList<>();

    @PrePersist
    void prePersist() {
        Instant n = Instant.now();
        if (createdAt == null) {
            createdAt = n;
        }
        updatedAt = n;
        if (yieldLossPercent == null) {
            yieldLossPercent = BigDecimal.ZERO;
        }
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = Instant.now();
    }

}

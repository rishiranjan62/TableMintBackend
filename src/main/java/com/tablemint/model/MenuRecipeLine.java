package com.tablemint.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "menu_recipe_line")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuRecipeLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_recipe_id", nullable = false)
    private MenuRecipe recipe;

    @Column(name = "inventory_item_id", nullable = false)
    private Long inventoryItemId;

    /**
     * Base quantity of this ingredient per one menu item sold (before loss factors).
     */
    @Column(name = "quantity_per_portion", nullable = false, precision = 18, scale = 6)
    private BigDecimal quantityPerPortion;

    /**
     * Unit of {@link #quantityPerPortion} when different from the inventory SKU's stock unit (e.g. "g" while stock is
     * kg). Null/blank means quantity is already in the SKU's quantity_unit.
     */
    @Column(name = "portion_quantity_unit", length = 32)
    private String portionQuantityUnit;

    /**
     * Line-level yield loss % (trim, spoilage during prep for this ingredient).
     */
    @Column(name = "yield_loss_percent", nullable = false, precision = 8, scale = 4)
    @Builder.Default
    private BigDecimal yieldLossPercent = BigDecimal.ZERO;

}

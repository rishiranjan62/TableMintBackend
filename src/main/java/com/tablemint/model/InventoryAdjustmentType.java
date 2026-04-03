package com.tablemint.model;

/**
 * How inventory quantity changes ( for audit trail)
 */
public enum InventoryAdjustmentType {
    /**
     * Consumption / end of day usage (reduces stock)
     */
    DAILY_USAGE,
    /**
     * Incoming delivery / restock (increases stock)
     */
    STOCK_IN,
    /**
     * Purchase receive with unit cost - updates weighted average cost (PO or goods receipt)
     */
    PURCHASE_RECEIPT,
    /**
     * Depletion from recipe / BOM when an order is served(or kitchen-ready if configured)
     */
    RECIPE_CONSUMPTION,
    /**
     * Spoilage,damage,waste for short shelf-life goods (reduces stock)
     */
    DAMAGE,
    /**
     * Physical count variance (positive or negative delta to match counted quantity)
     */
    STOCK_COUNT,
    /**
     * Migrated rows from the previous location-based model
     */
    LEGACY
}

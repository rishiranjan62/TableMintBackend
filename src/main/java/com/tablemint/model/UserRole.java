package com.tablemint.model;

public enum UserRole {

    CUSTOMER,
    ADMIN,
    KITCHEN_MANAGER,
    /**
     * Same operational permissions as {@link #KITCHEN_MANAGER} (kitchen / orders flow).
     */
    CHEF,
    FLOOR_MANAGER,
    RECEPTIONIST,
    /**
     * Cashier-Like access plus sales, full dashboard, menu CRUD, online orders; may list staff but not create/update/disable them.
     */
    MANAGER,
    /**
     * Chain / brand operator: reads roll-up analytics for all restaurants linked to {@code User.organizationId}.
     * •{@code restaurantId }must be null. Does not replace per-store ADMIN for operations.
     */
    ORG_ADMIN,
    /* TableMint internal admin; restaurantId null. Full/v1/platform/ including mutations. */
    PLATFORM_ADMIN,
    /**
     * TableMint internal read-only; restaurantId null. Same analytics as admin; cannot change tenants or manage users.
     */
    PLATFORM_MAINTAINER;

    /**
     * Platform portal accounts (no restaurant tenant).
     */
    public boolean isPlatformInternal() {
        return this == PLATFORM_ADMIN | this == PLATFORM_MAINTAINER;
    }
}

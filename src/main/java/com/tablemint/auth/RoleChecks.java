package com.tablemint.auth;

import com.tablemint.exception.ForbiddenException;
import com.tablemint.model.UserRole;

/**
 * Role-based access for staff: FM + RECEPTIONIST + MANAGER for sales/requests/tables; ADMIN, RECEPTIONIST, or MANAGER for dashboard/aggregator.
 */

public class RoleChecks {

    private RoleChecks() {
    }

    /**
     * Sales (except summary-by-source), requests, tables, orders: ADMIN, FLOOR_MANAGER, RECEPTIONIST, or MANAGER.
     */
    public static void requireAdminOrFloorManagerOrReceptionist(UserRole role) {
        if (role != UserRole.ADMIN && role != UserRole.FLOOR_MANAGER && role != UserRole.RECEPTIONIST && role != UserRole.MANAGER) {
            throw new ForbiddenException("This action is allowed only for Admin, Floor Manager, Receptionist, or Manager.");
        }
    }

    /**
     * Legacy: same as requireAdminOrFloorManagerOrReceptionist for backward compatibility.
     */
    public static void requireAdminOrFloorManager(UserRole role) {
        requireAdminOrFloorManagerOrReceptionist(role);
    }

    /**
     * Dashboard summary-by-source and aggregator orders: ADMIN, RECEPTIONIST, or MANAGER (not FM).
     */
    public static void requireAdminOrReceptionist(UserRole role) {
        if (role != UserRole.ADMIN && role != UserRole.RECEPTIONIST && role != UserRole.MANAGER) {
            throw new ForbiddenException("This action is allowed only for Admin, Receptionist, or Manager.");
        }
    }

    public static void requireAdmin(UserRole role) {
        if (role != UserRole.ADMIN) {
            throw new ForbiddenException("This action is allowed only for Admin.");
        }
    }

    public static void requireAdminOrManager(UserRole role) {
        if (role != UserRole.ADMIN && role != UserRole.MANAGER) {
            throw new ForbiddenException("This action is allowed only for Admin or Manager.");
        }
    }

    /**
     * Staff must be linked to a restaurant so audit, settings, and staff APIs are tenant-scoped.
     * Legacy accounts with null restaurant_id see all tenants until migration V5 or manual fix.
     */
    public static void requireStaffRestaurant(Long restaurantId) {
        if (restaurantId == null) {
            throw new ForbiddenException(
                    "Your staff account is not linked to a restaurant. Use an account created via restaurant onboarding, "
                            + "or if you have a single demo tenant, apply DB migration V5 and log in again.");
        }
    }


}

package com.tablemint.dto;

import com.tablemint.model.TableStatus;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class TableResponse {

    private Long id;
    /**
     * Tenant owning this table; used by customer app for menu/promos scoped to restaurant.
     */
    private Long restaurantId;
    /**
     * Per-restaurant table id (1, 2, 3..). Use for display; API paths still use {@code id}.
     */
    private Integer restaurantSequence;
    private String tableNumber;
    private String grCodeId;
    private TableStatus status;
    /**
     * Short note for FM/kitchen (e.g. "birthday", "allergy - nut-free").
     */
    private String notes;
    /**
     * When the table was first registered.
     */
    private Instant createdAt;
    /**
     * Last update (e.g. status change to OCCUPIED/AVAILABLE). Use this for occupancy.
     */
    private Instant updatedAt;
    /**
     * Current customer at this table (when OCCUPIED and customer has joined). For admin view.
     */
    private TableCustomerInfo currentCustomer;
    /**
     * Number of customers currently at this table (active occupancies). Show "group order" when > 1.
     */
    private Integer occupancyCount;
    /**
     * True if at least one order has been placed for this table. Staff: enable Bill/Pay only when true.
     */
    private Boolean hasPLacedOrder;

    private Boolean tableSharingEnabled;
}

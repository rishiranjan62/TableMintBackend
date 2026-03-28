package com.tablemint.dto;

import lombok.Data;

/** Assign or unassign floor/kitchen staff on an order. {@code userId} null clears  the assignment. */

@Data
public class AssignOrderStaffRequest {
    private Long userId;
}

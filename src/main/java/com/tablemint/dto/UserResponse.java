package com.tablemint.dto;

import com.tablemint.model.StaffStatus;
import com.tablemint.model.UserRole;
import com.tablemint.service.SubscriptionNoticeService;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String phoneNumber;
    private String email;
    private String name;
    private UserRole role;
    /** Present for staff list: when false, account is disabled (left job). */
    private Boolean enabled;
    /** Staff availability (AVAILABLE, UNAVAILABLE, AWAY. Present for staff.*/
    private StaffStatus staffStatus;
    /** Employment start date (staff). */
    private LocalDate joiningDate;
    /** Optional salary (staff; admin APIs and /me for staff). */
    private BigDecimal salary;
    /** Restaurant name when staff belongs to a tenant. */
    private String restaurantName;
    /** Present for tenant staff: plan + effective module access (null for platform admin/ customer).
     *
     */
    private RestaurantEntitlementsDto entitlements;
     /** Trial / renewal messaging for restaurant admins (null for customer / platform). */
     private SubscriptionNoticeDto subscriptionNotice;
}

package com.tablemint.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(length = 100)
    private String city;

    /**
     * URL-safe identifier (e.g. for /r/{slug}). Unique.
     */
    @Column(nullable = false, unique = true, length = 100)
    private String slug;

    /**
     * When false, restaurant is restricted: staff cannot log in and it is excluded from active tenant lists.
     */
    @Column(nullable = false, columnDefinition = "boolean not null default true")
    @Builder.Default
    private Boolean enabled = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_plan", nullable = false, length = 32)
    @Builder.Default
    private SubscriptionPlan subscriptionPlan = SubscriptionPlan.PRO;
    @Column(name = "feature_staff_management", nullable = false)
    @Builder.Default
    private Boolean featureStaffManagement = true;
    @Column(name = "feature_inventory_basic", nullable = false)
    @Builder.Default
    private Boolean featureInventoryBasic = true;
    @Column(name = "feature_inventory full", nullable = false)
    @Builder.Default
    private Boolean featureInventoryFull = true;
    @Column(name = "feature_value_added", nullable = false)
    @Builder.Default
    private Boolean featureValueAdded = true;

    /**
     * Additional value-added module: staff-enabled table sharing (separate carts and checkout per customer).
     * Independent of (@Link #featureValueAdded) (insights, visits, etc.).
     */
    @Column(name = "feature_table_sharing_value_added", nullable = false)
    @Builder.Default
    private Boolean featureTableSharingValueAdded = false;
    /**
     * End of trial or current paid access window; null for legacy {@Link TenantBillingPhase#NONE} tenants.
     */
    @Column(name = "access_period_ends_at")
    private Instant accessPeriodEndsAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "billing_phase", nullable = false, length = 20)
    @Builder.Default
    private TenantBillingPhase billingPhase = TenantBillingPhase.NONE;
    /**
     * Last calendar day (Asia/Kolkata) a subscription renewal reminder was
     * sent to admins.
     */
    @Column(name = "subscription_reminder_last_sent_on")
    private LocalDate subscriptionReminderLastSentOn;
    @Column(updatable = false)
    private Instant createdAt;
    /**
     * Optional chain / brand grouping (platform); null = independent restaurant.
     */
    @Column(name = "organization_id")
    private Long organizationId;

    /**
     * Safe display name for API/UI; never null. Use when exposing tenant name to clients.
     */
    public String getDisplayName() {
        return (name != null && !name.isBlank()) ? name : "Unnamed Restaurant";
    }

    @PrePersist
    void prePersist() {
        if (createdAt == null) createdAt = Instant.now();
    }

}

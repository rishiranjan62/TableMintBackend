package com.tablemint.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String phoneNumber;

    @Column(length = 255)
    private String email;

    @Column(length = 100)
    private String name;

    /** BCrypt hash for staff (ADMIN, KITCHEN_MANAGER, FLOOR_MANAGER). Null for customers (they use OTP). */
    @Column(name = "password_hash", length = 255)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20, columnDefinition = "VARCHAR(20) NOT NULL")
    private UserRole role;

    /** Restaurant (tenant) this staff belongs to. Null for platform internal and {@link UserRole#ORG_ADMIN}. */
    @Column(name = "restaurant_id")
    private Long restaurantId;

    /** Organization for {@link UserRole#ORG_ADMIN}; null for store staff and platform users. */
    @Column(name = "organization_id")
    private Long organizationId;

    /** When false, staff cannot log in (soft delete / left job). Default true. */
    @Column(nullable = false, columnDefinition = "boolean not null default true")
    @Builder.Default
    private Boolean enabled = true;

    /** Date the staff member joined (employment start). */
    @Column(name = "joining_date")
    private LocalDate joiningDate;

    /** Optional monthly salary (sensitive; staff admin APIs only). */
    @Column(precision = 12, scale = 2)
    private BigDecimal salary;

    /** Staff availability (AVAILABLE, UNAVAILABLE, AWAY). Null for customers. */
    @Enumerated(EnumType.STRING)
    @Column(name = "staff_status", length = 20)
    private StaffStatus staffStatus;

    /** 12-digit Aadhaar (staff; admin APIs only; not exposed on login / assignment pickers). */
    @Column(name = "aadhaar_number", length = 12)
    private String aadhaarNumber;

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

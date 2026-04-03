package com.tablemint.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Optional grouping for multi-site brands (chains). Restaurants reference this for platform reporting;
 * operations stay scoped to {@link Restaurant#getId()} per tenant.
 */
@Entity
@Table(name = "organizations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    /**
     * URL-safe unique key for platform links and APIs.
     */
    @Column(nullable = false, unique = true, length = 100)
    private String slug;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    void prePersist() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }

    public String getDisplayName() {
        return (name != null && !name.isBlank()) ? name : "Unnamed organization";
    }


}

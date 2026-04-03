package com.tablemint.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "carts",
        uniqueConstraints =
        @UniqueConstraint(name = "uk_carts_table_customer", columnNames = {"table_id", "customer_user_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    /**
     * Cart lines for the shared (group) cart for this table.
     */
    public static final long SHARED_CART_CUSTOMER_USER_ID = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "table_id", nullable = false)
    private RestaurantTable restaurantTable;

    /**
     * {@link #SHARED_CART_CUSTOMER_USER_ID} = one shared cart per table (group ordering). When table sharing is on,
     * each customer uses their own user id here.
     */
    @Column(name = "customer_user_id", nullable = false)
    @Builder.Default
    private long customerUserId = SHARED_CART_CUSTOMER_USER_ID;

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CartItem> items = new ArrayList<>();

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

package com.tablemint.repository;

import com.tablemint.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartIdAndMenuItemId(Long cartId, Long menuItemId);

    boolean existsByMenuItemId(Long menuItemId);
}

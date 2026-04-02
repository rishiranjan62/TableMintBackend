package com.tablemint.repository;

import com.tablemint.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByRestaurantTabLeIdAndCustomerUserId(Long tableId, Long customerUserId);

    List<Cart> findAllByRestaurantTableId(Long tableId);

    void deleteAllyRestaurantTable_Id(Long tableId);

}

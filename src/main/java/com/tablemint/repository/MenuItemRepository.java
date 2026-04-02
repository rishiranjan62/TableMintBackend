package com.tablemint.repository;

import com.tablemint.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {

    List<MenuItem> findByAvailableTrueAndDeletedFalse();

    List<MenuItem> findByCategoryAndAvailableTrueAndDeletedFalse(String category);

    List<MenuItem> findByCategoryAndDeletedFalse(String category);

    List<MenuItem> findByDeletedFalse();

    @Query("SELECT DISTINCT m.category FROM MenuItem m WHERE m.deleted = false AND m.category IS NOT NULL AND m.category != '' ORDER BY m.category")
    List<String> findDistinctCategories();

    /** Multi-tenant: list by restaurant (or legacy null). */
    List<MenuItem> findByRestaurantIdAndDeletedFalse(Long restaurantId);

    List<MenuItem> findByRestaurantIdAndAvailableTrueAndDeletedFalse(Long restaurantId);

    List<MenuItem> findByRestaurantIdAndCategoryAndDeletedFalse(Long restaurantId, String category);

    List<MenuItem> findByRestaurantIdAndCategoryAndAvailableTrueAndDeletedFalse(Long restaurantId, String category);

    List<MenuItem> findByRestaurantId(Long restaurantId);

    @Query("SELECT DISTINCT m.category FROM MenuItem m WHERE m.restaurantId = :restaurantId AND m.deleted = false AND m.category IS NOT NULL AND m.category != '' ORDER BY m.category")
    List<String> findDistinctCategoriesByRestaurantId(@org.springframework.data.repository.query.Param("restaurantId") Long restaurantId);

    /** Legacy: null restaurantId. */
    List<MenuItem> findByRestaurantIdIsNullAndDeletedFalse();

    List<MenuItem> findByRestaurantIdIsNullAndAvailableTrueAndDeletedFalse();

    List<MenuItem> findByRestaurantIdIsNullAndCategoryAndDeletedFalse(String category);

    List<MenuItem> findByRestaurantIdIsNullAndCategoryAndAvailableTrueAndDeletedFalse(String category);

    List<MenuItem> findByRestaurantIdIsNull();

    @Query("SELECT DISTINCT m.category FROM MenuItem m WHERE m.restaurantId IS NULL AND m.deleted = false AND m.category IS NOT NULL AND m.category != '' ORDER BY m.category")
    List<String> findDistinctCategoriesByRestaurantIdIsNull();

}

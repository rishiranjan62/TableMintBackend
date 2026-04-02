package com.tablemint.repository;

import com.tablemint.model.MenuRecipe;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuRecipeRepository extends JpaRepository<MenuRecipe, Long> {

    @EntityGraph(attributePaths = {"Lines"})
    List<MenuRecipe> findByRestaurantIdOrderByMenuItemIdAsc(Long restaurantId);

    Optional<MenuRecipe> findByIdAndRestaurantId(Long id, Long restaurantId);

    Optional<MenuRecipe> findByRestaurantIdAndMenuItemId(Long restaurantId, Long menuItemId);

    @Query(
            "select distinct r from MenuRecipe r left join fetch r. Lines where r.id = :id and r.restaurantId = :rid")
    Optional<MenuRecipe> findByIdAndRestaurantIdWithLines(@Param("id") Long id, @Param("rid") Long restaurantId);

}

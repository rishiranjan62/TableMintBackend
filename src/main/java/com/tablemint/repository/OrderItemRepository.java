package com.tablemint.repository;

import com.tablemint.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    boolean existsByMenuItemId(Long menuItemId);

    /**
     * For platform admin: quantity sold per menu item (and restaurant), PAID orders only.
     */
    @Query("SELECT oi.menuItem.id, oi.order.restaurantTable.restaurantId, SUM(oi.quantity) FROM OrderItem oi "
            + "WHERE oi.order.status = com.tableserve.model.OrderStatus.PAID "
            + "GROUP BY oi.menuItem.id, oi.order.restaurantTable.restaurantId")
    List<Object[]> sumQuantityByMenuItemAndRestaurant();

    @Query("SELECT oi.menuItem.id, oi.order.restaurantTable.restaurantId, SUM(oi.quantity) FROM OrderItem oi "
            + "WHERE oi.order.status = com.tableserve.model.OrderStatus.PAID AND oi.order.restaurantTable.restaurantId = :rid "
            + "GROUP BY oi.menuItem.id, oi.order.restaurantTable.restaurantId")
    List<Object[]> sumQuantityByMenuItemAndRestaurantId(@Param("rid") Long restaurantId);

    @Query("SELECT oi.menuItem.id, oi.order.restaurantTable.restaurantId, SUM(oi.quantity) FROM OrderItem oi "
            + "WHERE oi.order.status = com.tableserve.model.OrderStatus.PAID AND oi.order.updatedAt >= :from AND oi.order.updatedAt < :to "
            + "GROUP BY oi.menuItem.id, oi.order.restaurantTable.restaurantId")
    List<Object[]> sumQuantityByMenuItemAndRestaurantUpdatedBetween(@Param("from") Instant from, @Param("to") Instant to);

    @Query("SELECT oi.menuItem.id, oi.order.restaurantTable.restaurantId, SUM(oi.quantity) FROM OrderItem oi "
            + "WHERE oi.order.status = com.tableserve.model.OrderStatus.PAID AND oi.order.restaurantTable.restaurantId = :rid AND oi.order.updatedAt >= :from AND oi.order.updatedAt < :to "
            + "GROUP BY oi.menuItem.id, oi.order.restaurantTable.restaurantId")
    List<Object[]> sumQuantityByMenuItemAndRestaurantIdUpdatedBetween(@Param("rid") Long restaurantId, @Param("from") Instant from, @Param("to") Instant to);

    @Query("SELECT oi.menuItem.id, oi.order.restaurantTable.restaurantId, SUM(oi.quantity) FROM OrderItem oi "
            + "WHERE oi.order.status = com.tableserve.model.OrderStatus.PAID AND oi.order.restaurantTable.restaurantId IN :rids "
            + "GROUP BY oi.menuItem.id, oi.order.restaurantTable.restaurantId")
    List<Object[]> sumQuantityByMenuItemAndRestaurantIdsIn(@Param("rids") Collection<Long> restaurantIds);

    @Query("SELECT oi.menuItem.id, oi.order.restaurantTable.restaurantId, SUM(oi.quantity) FROM OrderItem oi "
            + "WHERE oi.order.status = com.tableserve.model.OrderStatus.PAID AND oi.order.restaurantTable.restaurantId IN :rids "
            + "AND oi.order.updatedAt >= :from AND oi.order.updatedAt < :to "
            + "GROUP BY oi.menuItem.id, oi.order.restaurantTable.restaurantId")
    List<Object[]> sumQuantityByMenuItemAndRestaurantIdsUpdatedBetween(
            @Param("rids") Collection<Long> restaurantIds, @Param("from") Instant from, @Param("to") Instant to);

    @Query("SELECT oi FROM OrderItem oi JOIN FETCH oi.order o JOIN FETCH o.restaurantTable t JOIN FETCH oi.menuItem m "
            + "WHERE oi.preparedAt >= :from AND oi.preparedAt < :to AND oi.assignedChefUserId IS NOT NULL "
            + "AND t.restaurantId = :rid AND (:chefId IS NULL OR oi.assignedChefUserId = :chefId) "
            + "ORDER BY oi.preparedAt DESC")
    List<OrderItem> findPreparedLinesInRange(
            @Param("rid") Long restaurantId,
            @Param("from") Instant from,
            @Param("to") Instant to,
            @Param("chefId") Long chefUserIdOrNull);

    @Query(
            value =
                    "SELECT m.id, m.name, "
                            + "AVG(TIMESTAMPDIFF(SECOND, oi.prep_started_at, oi.prepared_at)), "
                            + "COUNT(oi.id) "
                            + "FROM order_items oi "
                            + "INNER JOIN orders o ON oi.order_id = o.id "
                            + "INNER JOIN menu_items m ON oi.menu_item_id = m.id "
                            + "INNER JOIN restaurant_tables t ON o.table_id = t.id "
                            + "WHERE o.status = 'PAID' AND t.restaurant_id = :rid "
                            + "AND o.updated_at >= :from AND o.updated_at < :to "
                            + "AND oi.prep_started_at IS NOT NULL AND oi.prepared_at IS NOT NULL "
                            + "AND oi.prepared_at >= oi.prep_started_at "
                            + "GROUP BY m.id, m.name "
                            + "ORDER BY COUNT(oi.id) DESC "
                            + "LIMIT 40",
            nativeQuery = true)
    List<Object[]> avgPrepSecondsByMenuItemPaidInRange(
            @Param("rid") Long restaurantId, @Param("from") Instant from, @Param("to") Instant to);


}

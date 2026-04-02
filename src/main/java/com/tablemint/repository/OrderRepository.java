package com.tablemint.repository;


import com.tablemint.model.Order;
import com.tablemint.model.OrderSource;
import com.tablemint.model.OrderStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {


    Page<Order> findAllByOrderByCreatedAtAsc(Pageable pageable);

    Page<Order> findByStatusOrderByCreatedAtAsc(OrderStatus status, Pageable pageable);

    Page<Order> findByRestaurantTableIdOrderByCreatedAtDesc(Long tableId, Pageable pageable);

    List<Order> findByRestaurantTableIdAndStatusAndPaidAtBetweenOrderByCreatedAtAsc(
            Long tableId, OrderStatus status, Instant paidAtFrom, Instant paidAtTo);

    boolean existsByRestaurantTable_IdAndStatusAndPaidAtBetween(
            Long tableId, OrderStatus status, Instant paidAtFrom, Instant paidAtTo);

    List<Order> findByRestaurantTableIdOrderByCreatedAtDesc(Long tableId);

    /** True if the table has at least one order (any status). */
    boolean existsByRestaurantTableId(Long restaurantTableId);

    /**
     * True if the table has at least one order whose status is not in {@code excludedStatuses}
     * (e.g. exclude PAID and CANCELLED for "current visit has something to bill").
     */
    boolean existsByRestaurantTableIdAndStatusNotIn(Long restaurantTableId, Collection<OrderStatus> excludedStatuses);

    /** Orders placed by this customer (for "My Orders"). */
    List<Order> findByUserIdOrderByCreatedAtDesc(Long userId);

    /** Unpaid orders for a table (excludes PAID and CANCELLED). */
    List<Order> findByRestaurantTableIdAndStatusInOrderByCreatedAtAsc(
            Long tableId, List<OrderStatus> statuses);

    List<Order> findByStatusInOrderByCreatedAtAsc(List<OrderStatus> statuses);

    Page<Order> findByStatusInOrderByCreatedAtAsc(List<OrderStatus> statuses, Pageable pageable);

    List<Order> findByStatusOrderByCreatedAtAsc(OrderStatus status);

    List<Order> findByStatusAndCreatedAtBetweenOrderByCreatedAtAsc(
            OrderStatus status, Instant from, Instant to);

    List<Order> findByStatusAndRestaurantTableIdAndCreatedAtBetweenOrderByCreatedAtAsc(
            OrderStatus status, Long tableId, Instant from, Instant to);

    /** For sales by date/week: filter by when order was marked PAID (updatedAt). */
    List<Order> findByStatusAndUpdatedAtBetweenOrderByUpdatedAtAsc(
            OrderStatus status, Instant from, Instant to);

    Page<Order> findByStatusAndUpdatedAtBetweenOrderByUpdatedAtAsc(
            OrderStatus status, Instant from, Instant to, Pageable pageable);

    List<Order> findByStatusAndRestaurantTableIdAndUpdatedAtBetweenOrderByUpdatedAtAsc(
            OrderStatus status, Long tableId, Instant from, Instant to);

    Page<Order> findByStatusAndRestaurantTableIdAndUpdatedAtBetweenOrderByUpdatedAtAsc(
            OrderStatus status, Long tableId, Instant from, Instant to, Pageable pageable);

    long countByStatusAndUpdatedAtBetween(OrderStatus status, Instant from, Instant to);

    long countByStatusAndRestaurantTableIdAndUpdatedAtBetween(OrderStatus status, Long tableId, Instant from, Instant to);

    long countByStatus(OrderStatus status);

    Page<Order> findByStatusAndRestaurantTableIdOrderByUpdatedAtDesc(OrderStatus status, Long tableId, Pageable pageable);

    long countByStatusAndRestaurantTableId(OrderStatus status, Long tableId);

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE o.status = :status AND o.restaurantTable.id = :tableId")
    BigDecimal sumTotalAmountByStatusAndRestaurantTableId(@Param("status") OrderStatus status, @Param("tableId") Long tableId);

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE o.status = :status AND o.updatedAt >= :from AND o.updatedAt < :to")
    BigDecimal sumTotalAmountByStatusAndUpdatedAtBetween(@Param("status") OrderStatus status, @Param("from") Instant from, @Param("to") Instant to);

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE o.status = :status AND o.restaurantTable.id = :tableId AND o.updatedAt >= :from AND o.updatedAt < :to")
    BigDecimal sumTotalAmountByStatusAndTableAndUpdatedAtBetween(@Param("status") OrderStatus status, @Param("tableId") Long tableId, @Param("from") Instant from, @Param("to") Instant to);

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE o.status = :status")
    BigDecimal sumTotalAmountByStatus(@Param("status") OrderStatus status);

    /** Aggregator (manual) orders for admin list; filter by PAID and date range. */
    List<Order> findByStatusAndOrderSourceAndUpdatedAtBetweenOrderByUpdatedAtDesc(
            OrderStatus status, OrderSource orderSource, Instant from, Instant to);

    Page<Order> findByStatusAndOrderSourceAndUpdatedAtBetweenOrderByUpdatedAtDesc(
            OrderStatus status, OrderSource orderSource, Instant from, Instant to, Pageable pageable);

    /** Multi-tenant: orders for tables in this restaurant. */
    Page<Order> findByRestaurantTable_RestaurantIdOrderByCreatedAtAsc(Long restaurantId, Pageable pageable);

    Page<Order> findByRestaurantTable_RestaurantIdAndStatusOrderByCreatedAtAsc(Long restaurantId, OrderStatus status, Pageable pageable);

    List<Order> findByRestaurantTable_RestaurantIdAndStatusInOrderByCreatedAtAsc(Long restaurantId, List<OrderStatus> statuses);

    Page<Order> findByRestaurantTable_RestaurantIdAndStatusInOrderByCreatedAtAsc(Long restaurantId, List<OrderStatus> statuses, Pageable pageable);

    /** Legacy: orders for tables with null restaurantId. */
    Page<Order> findByRestaurantTable_RestaurantIdIsNullOrderByCreatedAtAsc(Pageable pageable);

    Page<Order> findByRestaurantTable_RestaurantIdIsNullAndStatusOrderByCreatedAtAsc(OrderStatus status, Pageable pageable);

    List<Order> findByRestaurantTable_RestaurantIdIsNullAndStatusInOrderByCreatedAtAsc(List<OrderStatus> statuses);

    Page<Order> findByRestaurantTable_RestaurantIdIsNullAndStatusInOrderByCreatedAtAsc(List<OrderStatus> statuses, Pageable pageable);

    /** For sales: PAID orders in date range, scoped by restaurant. */
    List<Order> findByRestaurantTable_RestaurantIdAndStatusAndUpdatedAtBetweenOrderByUpdatedAtAsc(Long restaurantId, OrderStatus status, Instant from, Instant to);

    List<Order> findByRestaurantTable_RestaurantIdIsNullAndStatusAndUpdatedAtBetweenOrderByUpdatedAtAsc(OrderStatus status, Instant from, Instant to);

    long countByRestaurantTable_RestaurantIdAndStatusAndUpdatedAtBetween(Long restaurantId, OrderStatus status, Instant from, Instant to);

    long countByRestaurantTable_RestaurantIdIsNullAndStatusAndUpdatedAtBetween(OrderStatus status, Instant from, Instant to);

    Page<Order> findByRestaurantTable_RestaurantIdAndStatusAndUpdatedAtBetweenOrderByUpdatedAtAsc(
            Long restaurantId, OrderStatus status, Instant from, Instant to, Pageable pageable);

    Page<Order> findByRestaurantTable_RestaurantIdIsNullAndStatusAndUpdatedAtBetweenOrderByUpdatedAtAsc(
            OrderStatus status, Instant from, Instant to, Pageable pageable);


    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE o.status = :st AND o.restaurantTable.restaurantId = :rid AND o.updatedAt >= :f AND o.updatedAt < :t")
    BigDecimal sumTotalAmountPaidByRestaurantUpdatedBetween(
            @Param("st") OrderStatus status, @Param("rid") Long restaurantId, @Param("f") Instant from, @Param("t") Instant to);

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE o.status = :st AND o.restaurantTable.restaurantId IS NULL AND o.updatedAt >= :f AND o.updatedAt < :t")
    BigDecimal sumTotalAmountPaidLegacyUpdatedBetween(
            @Param("st") OrderStatus status, @Param("f") Instant from, @Param("t") Instant to);

    Page<Order> findByRestaurantTable_RestaurantIdAndStatusOrderByUpdatedAtDesc(
            Long restaurantId, OrderStatus status, Pageable pageable);

    long countByRestaurantTable_RestaurantIdAndStatus(Long restaurantId, OrderStatus status);

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE o.status = :st AND o.restaurantTable.restaurantId = :rid")
    BigDecimal sumTotalAmountByStatusAndRestaurantTable_RestaurantId(
            @Param("st") OrderStatus status, @Param("rid") Long restaurantId);

    Page<Order> findByRestaurantTable_RestaurantIdIsNullAndStatusOrderByUpdatedAtDesc(OrderStatus status, Pageable pageable);

    long countByRestaurantTable_RestaurantIdIsNullAndStatus(OrderStatus status);

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE o.status = :st AND o.restaurantTable.restaurantId IS NULL")
    BigDecimal sumTotalAmountByStatusAndRestaurantTable_RestaurantIdIsNull(@Param("st") OrderStatus status);

    /** Chef board: all CONFIRMED in restaurant; PREPARING/READY only if assigned on ticket or a line. */
    @Query("SELECT DISTINCT o FROM Order o WHERE o.restaurantTable.restaurantId = :rid AND o.status IN :statuses "
            + "AND (o.status = com.tableserve.model.OrderStatus.CONFIRMED "
            + "OR o.assignedChefUserId = :chefId "
            + "OR EXISTS (SELECT 1 FROM OrderItem oi WHERE oi.order = o AND oi.assignedChefUserId = :chefId))")
    Page<Order> findKitchenOrdersAssignedToChef(
            @Param("rid") Long restaurantId,
            @Param("statuses") List<OrderStatus> statuses,
            @Param("chefId") Long chefUserId,
            Pageable pageable);

    @Query("SELECT DISTINCT o FROM Order o WHERE o.restaurantTable.restaurantId IS NULL AND o.status IN :statuses "
            + "AND (o.status = com.tableserve.model.OrderStatus.CONFIRMED "
            + "OR o.assignedChefUserId = :chefId "
            + "OR EXISTS (SELECT 1 FROM OrderItem oi WHERE oi.order = o AND oi.assignedChefUserId = :chefId))")
    Page<Order> findKitchenOrdersAssignedToChefLegacy(
            @Param("statuses") List<OrderStatus> statuses,
            @Param("chefId") Long chefUserId,
            Pageable pageable);

    /** Manager: full READY tickets, or PREPARING with at least one line prepared (partial service handoff). */
    @Query("SELECT DISTINCT o FROM Order o WHERE o.restaurantTable.restaurantId = :rid "
            + "AND (o.status = com.tableserve.model.OrderStatus.READY "
            + "OR (o.status = com.tableserve.model.OrderStatus.PREPARING "
            + "AND EXISTS (SELECT 1 FROM OrderItem oi WHERE oi.order = o AND oi.preparedAt IS NOT NULL))) "
            + "ORDER BY o.createdAt ASC")
    List<Order> findOrdersForServeHandoff(@Param("rid") Long restaurantId);

    @Query("SELECT DISTINCT o FROM Order o WHERE o.restaurantTable.restaurantId IS NULL "
            + "AND (o.status = com.tableserve.model.OrderStatus.READY "
            + "OR (o.status = com.tableserve.model.OrderStatus.PREPARING "
            + "AND EXISTS (SELECT 1 FROM OrderItem oi WHERE oi.order = o AND oi.preparedAt IS NOT NULL))) "
            + "ORDER BY o.createdAt ASC")
    List<Order> findOrdersForServeHandoffLegacy();

    /** Chef performance: whole-ticket orders marked READY with an order-level chef (lines may have no prepared_at yet). */
    @Query("SELECT o FROM Order o WHERE o.restaurantTable.restaurantId = :rid "
            + "AND o.kitchenReadyAt >= :from AND o.kitchenReadyAt < :to AND o.assignedChefUserId IS NOT NULL")
    List<Order> findOrdersWithKitchenReadyInRange(
            @Param("rid") Long restaurantId,
            @Param("from") Instant from,
            @Param("to") Instant to);

    @Query("SELECT o FROM Order o WHERE o.restaurantTable.restaurantId IS NULL "
            + "AND o.kitchenReadyAt >= :from AND o.kitchenReadyAt < :to AND o.assignedChefUserId IS NOT NULL")
    List<Order> findOrdersWithKitchenReadyInRangeLegacy(
            @Param("from") Instant from,
            @Param("to") Instant to);

    /** Staff performance: orders served in range with an assigned floor waiter. */
    @Query("SELECT o FROM Order o WHERE o.restaurantTable.restaurantId = :rid "
            + "AND o.servedAt IS NOT NULL AND o.servedAt >= :from AND o.servedAt < :to "
            + "AND o.assignedWaiterUserId IS NOT NULL")
    List<Order> findServedOrdersWithWaiterInRange(
            @Param("rid") Long restaurantId, @Param("from") Instant from, @Param("to") Instant to);

    @Query("SELECT o FROM Order o WHERE o.restaurantTable.restaurantId IS NULL "
            + "AND o.servedAt IS NOT NULL AND o.servedAt >= :from AND o.servedAt < :to "
            + "AND o.assignedWaiterUserId IS NOT NULL")
    List<Order> findServedOrdersWithWaiterInRangeLegacy(@Param("from") Instant from, @Param("to") Instant to);

    /** Staff performance: tickets that became kitchen-ready in range (items loaded for line-chef credit). */
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.items WHERE o.restaurantTable.restaurantId = :rid "
            + "AND o.kitchenReadyAt IS NOT NULL AND o.kitchenReadyAt >= :from AND o.kitchenReadyAt < :to")
    List<Order> findKitchenReadyOrdersInRangeWithItems(
            @Param("rid") Long restaurantId, @Param("from") Instant from, @Param("to") Instant to);

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.items WHERE o.restaurantTable.restaurantId IS NULL "
            + "AND o.kitchenReadyAt IS NOT NULL AND o.kitchenReadyAt >= :from AND o.kitchenReadyAt < :to")
    List<Order> findKitchenReadyOrdersInRangeWithItemsLegacy(@Param("from") Instant from, @Param("to") Instant to);

    @Query(
            "SELECT oi.menuItem.id, SUM(oi.quantity) FROM OrderItem oi JOIN oi.order o "
                    + "WHERE o.restaurantId = :rid AND o.servedAt IS NOT NULL AND o.servedAt >= :from AND o.servedAt < :to "
                    + "AND o.status IN :stati GROUP BY oi.menuItem.id")
    List<Object[]> sumSoldQtyByMenuItemServedBetween(
            @Param("rid") Long restaurantId,
            @Param("from") Instant fromInclusive,
            @Param("to") Instant toExclusive,
            @Param("stati") List<OrderStatus> statuses);

    @Query(
            "SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.items i LEFT JOIN FETCH i.menuItem "
                    + "LEFT JOIN FETCH o.restaurantTable WHERE o.id = :id")
    Optional<Order> findByIdWithItemsAndMenu(@Param("id") Long id);

    /** PAID orders in range with lines + menu (value-added insights; tenant-scoped). */
    @Query(
            "SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.items i LEFT JOIN FETCH i.menuItem "
                    + "LEFT JOIN FETCH o.restaurantTable WHERE o.restaurantTable.restaurantId = :rid "
                    + "AND o.status = com.tableserve.model.OrderStatus.PAID "
                    + "AND o.updatedAt >= :from AND o.updatedAt < :to "
                    + "ORDER BY o.updatedAt ASC")
    List<Order> findPaidWithItemsForInsights(
            @Param("rid") Long restaurantId, @Param("from") Instant from, @Param("to") Instant to);

}

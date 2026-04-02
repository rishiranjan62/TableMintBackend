package com.tablemint.repository;

import com.tablemint.model.RestaurantTable;
import com.tablemint.model.TableStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE RestaurantTable t SET t.serviceChargeWaived = :waived WHERE t.id = :tableId AND t.status = 'OCCUPIED'")
    int setServiceChargeWaivedForOccupiedTable(@Param("tableId") Long tableId, @Param("waived") Boolean waived);

    @Query("SELECT t.serviceChargeWaived FROM RestaurantTable t WHERE t.id = :tableId")
    Optional<Boolean> findServiceChargeWaivedByTableId(@Param("tableId") Long tableId);

    Optional<RestaurantTable> findByQrCodeId(String qrCodeId);

    List<RestaurantTable> findAllByQrCodeId(String qrCodeId);

    Optional<RestaurantTable> findByRestaurantIdAndQrCodeId(Long restaurantId, String qrCodeId);

    Optional<RestaurantTable> findByRestaurantIdIsNullAndQrCodeId(String qrCodeId);

    Optional<RestaurantTable> findByTableNumber(String tableNumber);

    List<RestaurantTable> findByStatus(TableStatus status);

    boolean existsByTableNumber(String tableNumber);

    boolean existsByQrCodeId(String qrCodeId);

    /**
     * Multi-tenant: list tables for a restaurant, or legacy (null restaurantId).
     */
    List<RestaurantTable> findByRestaurantId(Long restaurantId);

    List<RestaurantTable> findByRestaurantIdIsNull();

    boolean existsByRestaurantIdAndTableNumber(Long restaurantId, String tableNumber);

    boolean existsByRestaurantIdIsNullAndTableNumber(String tableNumber);

    boolean existsByRestaurantIdAndQrCodeId(Long restaurantId, String qrCodeId);

    boolean existsByRestaurantIdIsNullAndQrCodeId(String qrCodeId);

    Optional<RestaurantTable> findByRestaurantIdAndTableNumber(Long restaurantId, String tableNumber);

    Optional<RestaurantTable> findByRestaurantIdIsNullAndTableNumber(String tableNumber);

    @Query("SELECT COALESCE(MAX(t.restaurantSequence), 0) FROM RestaurantTable t WHERE t.restaurantId = :rid")
    int findMaxRestaurantSequenceByRestaurantId(@Param("rid") Long rid);

    @Query("SELECT COALESCE(MAX(t.restaurantSequence), 0) FROM RestaurantTable t WHERE t.restaurantId IS NULL")
    int findMaxRestaurantSequenceForNullRestaurant();

    @Query("SELECT DISTINCT t.restaurantId FROM RestaurantTable t WHERE t.restaurantId IS NOT NULL")
    List<Long> findDistinctRestaurantIds();
}

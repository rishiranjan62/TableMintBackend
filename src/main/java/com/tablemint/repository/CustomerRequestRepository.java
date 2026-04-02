package com.tablemint.repository;

import com.tablemint.model.CustomerRequest;
import com.tablemint.model.CustomerRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRequestRepository extends JpaRepository<CustomerRequest,Long> {

    List<CustomerRequest> findByStatusInOrderByCreatedAtAsc(List<CustomerRequestStatus> statuses);

    List<CustomerRequest> findByRestaurantTableIdAndStatusInOrderByCreatedAtDesc(Long tableId,List<CustomerRequestStatus> statuses);

    void deleteByRestaurantTableId(Long tableId);

    List<CustomerRequest> findByRestaurantTable_RestaurantIdAndStatusInOrderByCreatedAtAsc(Long restaurantId,List<CustomerRequestStatus> statuses);

    List<CustomerRequest> findByRestaurantTable_RestaurantIdIsNullAndStatusInOrderByCreatedAtAsc(List<CustomerRequestStatus> statuses);


}

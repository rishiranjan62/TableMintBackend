package com.tablemint.repository;

import com.tablemint.model.RestaurantLeavePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantLeavePolicyRepository extends JpaRepository<RestaurantLeavePolicy,Long> {
}

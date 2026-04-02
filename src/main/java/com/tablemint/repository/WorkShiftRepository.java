package com.tablemint.repository;

import com.tablemint.model.ShiftKind;
import com.tablemint.model.WorkShift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkShiftRepository extends JpaRepository<WorkShift, Long> {

    List<WorkShift> findByRestaurantIdAndActiveTrueOrderBySortOrderAscNameAsc(Long restaurantId);

    List<WorkShift> findByRestaurantIdOrderBySortOrderAscNameAsc(Long restaurantId);

    Optional<WorkShift> findByRestaurantIdAndShiftKind(Long restaurantId, ShiftKind shiftKind);

}

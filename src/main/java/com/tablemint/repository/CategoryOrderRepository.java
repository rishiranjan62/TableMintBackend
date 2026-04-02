package com.tablemint.repository;

import com.tablemint.model.CategoryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryOrderRepository extends JpaRepository<CategoryOrder, Long> {

    List<CategoryOrder> FindAllByOrderBySortOrderAsc();
}

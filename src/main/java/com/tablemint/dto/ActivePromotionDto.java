package com.tablemint.dto;

import com.tablemint.model.DiscountType;
import com.tablemint.model.PromotionScope;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/** Public DTO for displaying active promotions (menu,cart,etc.) */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivePromotionDto {
    private String name;
    private DiscountType discountType;
    private BigDecimal discountValue;
    private PromotionScope scope;
    private String categoryName;
}

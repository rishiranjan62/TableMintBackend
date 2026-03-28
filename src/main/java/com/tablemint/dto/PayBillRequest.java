package com.tablemint.dto;

import com.tablemint.model.DiscountType;
import com.tablemint.model.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayBillRequest {

    private PaymentMethod paymentMethod;

    @DecimalMin(value = "0.0", message = "Amount tendered can't be negative")
    private BigDecimal amountTendered;

    /**
     * Optional discount at checkout : percentage or fixed
     */
    private DiscountType discountType;

    @DecimalMin(value = "0.0", message = "Discount value can't be negative")
    private BigDecimal discountValue;
}

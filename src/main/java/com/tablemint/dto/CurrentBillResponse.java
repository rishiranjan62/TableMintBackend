package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CurrentBillResponse {

    private Long tableId;
    private String tableNumber;
    private List<OrderResponse> orders;
    private int orderCount;
    /**
     * All ordered items (flattened from orders) for bill display: name, quantity, unit price, line total.
     */
    private List<OrderItemResponse> lineItems;

    /**
     * Sum of all order totals before tax/service charge.
     */
    private BigDecimal subtotal;
    /**
     * CGST % (from config).
     */
    private BigDecimal cgstPercent;
    /**
     * CGST amount (subtotal * cgstPercent/100).
     */
    private BigDecimal cgstAmount;
    /**
     * SGST % (from config).
     */
    private BigDecimal sgstPercent;
    /**
     * SGST amount (subtotal * sgstPercent/100).
     */
    private BigDecimal sgstAmount;
    /**
     * Total tax = CGST + SGST.
     */
    private BigDecimal taxAmount;
    /**
     * Service charge percentage applied (from config).
     */
    private BigDecimal serviceChargePercent;
    /**
     * Service charge amount (subtotal * serviceChargePercent/100).
     */
    private BigDecimal serviceChargeAmount;
    /**
     * Discount applied at pay-bill; 0 when viewing bill.
     */
    private BigDecimal discountAmount;
    /**
     * Final amount to pay = subtotal + tax + serviceCharge - discount. Same as payableAmount.
     */
    private BigDecimal totalAmount;
    /**
     * Payable amount (final amount to pay).
     */
    private BigDecimal payableAmount;

    /**
     * When true, this is the last paid bill (view only). When false, unpaid current bill.
     */
    private Boolean paid;

    /**
     * When false, service charge was waived for this table (admin on billing page). Same bill shown to customer.
     */
    private Boolean applyServiceCharge;

}

package com.tablemint.dto;

import com.tablemint.model.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class ReceiptResponse {

    /**
     * Unique receipt id for reference and audits.
     */
    private String receiptId;
    /**
     * When the receipt was generated (pay-bill time).
     */
    private Instant generatedAt;

    private Long tableId;
    private String tableNumber;
    private List<ReceiptLineItem> lineItems;
    private BigDecimal subtotal;
    private BigDecimal cgstPercent;
    private BigDecimal cgstAmount;
    private BigDecimal sgstPercent;
    private BigDecimal sgstAmount;
    private BigDecimal taxAmount;
    private BigDecimal serviceChargePercent;
    private BigDecimal serviceChargeAmount;
    private BigDecimal discountAmount;
    /**
     * Final amount to pay. Same as payableAmount.
     */
    private BigDecimal totalAmount;
    /**
     * Payable amount (final amount to pay).
     */
    private BigDecimal payableAmount;
    private PaymentMethod paymentMethod;
    private BigDecimal amountTendered;
}

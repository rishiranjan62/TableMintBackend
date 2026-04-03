package com.tablemint.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Stored receipt summary for audits: receipt id, generated at, table, totals, payment method.
 */
@Entity
@Table(name = "receipts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "receipt_id", unique = true, nullable = false, length = 36)
    private String receiptId;

    @Column(name = "generated_at", nullable = false)
    private Instant generatedAt;

    @Column(name = "table_id", nullable = false)
    private Long tableId;

    @Column(name = "table_number", nullable = false, length = 20)
    private String tableNumber;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "cgst_amount", precision = 12, scale = 2)
    private BigDecimal cgstAmount;

    @Column(name = "sgst_amount", precision = 12, scale = 2)
    private BigDecimal sgstAmount;

    @Column(name = "tax_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal taxAmount;

    @Column(name = "service_charge_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal serviceChargeAmount;

    @Column(name = "discount_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "total_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "payable_amount", precision = 12, scale = 2)
    private BigDecimal payableAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", length = 20)
    private PaymentMethod paymentMethod;

    /**
     * Comma-separated order ids included in this receipt (split checkout / precise reconstruction).
     */
    @Column(name = "paid_order_ids", length = 768)
    private String paidOrderIds;

    /**
     * When set, this receipt is for that customer's split checkout; null = whole-table (legacy) receipt.
     */
    @Column(name = "for_customer_user_id")
    private Long forCustomerUserId;

}

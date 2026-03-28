package com.tablemint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminSettingsDto {

    /**
     * Open time 24h, e.g. "09:00".
     */
    private String openTime;
    /**
     * Close time 24h, e.g. "22:00".
     */
    private String closeTime;
    /**
     * CGST % on subtotal, e.g. 2.5.
     */
    private BigDecimal cgstPercent;
    /**
     * SGST % on subtotal, e.g. 2.5.
     */
    private BigDecimal sgstPercent;
    /**
     * Service charge % on subtotal, e.g. 10.
     */
    private BigDecimal serviceChargePercent;
    // When false, service charge is not applied to bills (optional per customer request)
    private Boolean serviceChargeEnabled;

    /**
     * Recipe BOM stock deduction: OFF (manual only), SERVED (when order marked served), READY (when kitchen marks
     * ready ).
     * */

    private  String inventoryAutoDeduct;
}

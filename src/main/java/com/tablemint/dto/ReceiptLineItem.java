package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ReceiptLineItem {
    private String menuItemName;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal lineTotal;
    private String lineNotes;

}

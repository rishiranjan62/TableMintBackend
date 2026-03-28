package com.tablemint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillOptionsRequest {

    /** When false, service charge is waived for this table's bill (same bill shown to customer).*/
    private Boolean applyServiceCharge;
}

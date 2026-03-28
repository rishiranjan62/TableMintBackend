package com.tablemint.dto;

import lombok.Data;

@Data
public class ClockInRequest {

    /** Optional : when set, used to evaluate late arrival against shift start + grace */
    private  Long shiftId;
}

package com.tablemint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecentCustomerOtpEntry {
    String phoneNumber;
    String otp;
    Instant requestedAt;
    String tableNumber;
    Long restaurantId;
}

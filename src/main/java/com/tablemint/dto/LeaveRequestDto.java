package com.tablemint.dto;

import com.tablemint.model.LeaveRequestStatus;
import com.tablemint.model.LeaveRequestType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
public class LeaveRequestDto {

    private Long id;
    private Long userId;
    private String staffName;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalDays;
    private LeaveRequestType requestType;
    private LeaveRequestStatus status;
    private String reason;
    private Long reviewerUserId;
    private Instant reviewedAt;
    private String reviewNote;
    private Instant createdAt;
}

package com.tablemint.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "staff_attendance", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "work_date"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "work_date", nullable = false)
    private LocalDate workDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shift_id")
    private WorkShift shift;

    @Column(name = "clock_in_at", nullable = false)
    private Instant clockInAt;

    @Column(name = "clock_out_at")
    private Instant clockOutAt;

    @Column(name = "late_arrival", nullable = false)
    @Builder.Default
    private Boolean lateArrival = false;

    @Column(name = "late_minutes")
    private Integer lateMinutes;

}

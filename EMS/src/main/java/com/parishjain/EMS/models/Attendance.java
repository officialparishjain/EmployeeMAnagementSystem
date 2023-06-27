package com.parishjain.EMS.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;
    @Column(nullable = false)
    private LocalDate localDate;

    private Long employeeId;
    @Enumerated(EnumType.STRING)
    private Status status;



    public Attendance(Long employeeId,Status status) {
        this.localDate = LocalDate.now();
        this.employeeId = employeeId;
        this.status = status;
    }
}
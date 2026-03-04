package com.marwa.appointment_api.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(optional = false)
    private Doctor doctor;


    @ManyToOne(optional = false)
    private Patient patient;

    @NotNull
    @Future
    @Column(nullable = false)
    private LocalDateTime startTime;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status = AppointmentStatus.SCHEDULED;

    private String notes;
}

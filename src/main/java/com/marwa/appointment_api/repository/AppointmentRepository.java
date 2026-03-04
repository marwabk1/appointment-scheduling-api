package com.marwa.appointment_api.repository;
import com.marwa.appointment_api.entity.Appointment;
import com.marwa.appointment_api.entity.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
public interface AppointmentRepository extends  JpaRepository<Appointment, Long>{
    boolean existsByDoctor_IdAndStatusAndStartTimeLessThanAndEndTimeGreaterThan(
            Long doctorId,
            AppointmentStatus status,
            LocalDateTime endTime,
            LocalDateTime startTime
    );
}

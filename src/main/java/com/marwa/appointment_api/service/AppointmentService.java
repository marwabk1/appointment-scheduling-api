package com.marwa.appointment_api.service;
import com.marwa.appointment_api.entity.*;
import com.marwa.appointment_api.repository.AppointmentRepository;
import com.marwa.appointment_api.repository.DoctorRepository;
import com.marwa.appointment_api.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              DoctorRepository doctorRepository,
                              PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public Appointment book(Long doctorId, Long patientId, Appointment appointment) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found: " + doctorId));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found: " + patientId));

        if (appointment.getStartTime().isAfter(appointment.getEndTime()) ||
                appointment.getStartTime().isEqual(appointment.getEndTime())) {
            throw new RuntimeException("Invalid time range: startTime must be before endTime");
        }

        boolean overlap = appointmentRepository.existsByDoctor_IdAndStatusAndStartTimeLessThanAndEndTimeGreaterThan(
                doctorId,
                AppointmentStatus.SCHEDULED,
                appointment.getEndTime(),
                appointment.getStartTime()
        );

        if (overlap) {
            throw new IllegalArgumentException("Doctor is already booked in that time slot");
        }

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setStatus(AppointmentStatus.SCHEDULED);

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public Appointment cancel(Long appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found: " + appointmentId));

        appointment.setStatus(AppointmentStatus.CANCELLED);

        return appointmentRepository.save(appointment);
    }
}

package com.marwa.appointment_api.controller;
import com.marwa.appointment_api.entity.Appointment;
import com.marwa.appointment_api.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")

public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment bookAppointment(
            @RequestParam Long doctorId,
            @RequestParam Long patientId,
            @Valid @RequestBody Appointment appointment
    ) {
        return appointmentService.book(doctorId, patientId, appointment);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAll();
    }
    @PutMapping("/{id}/cancel")
    public Appointment cancelAppointment(@PathVariable Long id) {
        return appointmentService.cancel(id);
    }
}

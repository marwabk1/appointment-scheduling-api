package com.marwa.appointment_api.controller;

import com.marwa.appointment_api.entity.Doctor;
import com.marwa.appointment_api.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor createDoctor(@Valid @RequestBody Doctor doctor) {
        return doctorService.createDoctor(doctor);
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }
}

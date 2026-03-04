package com.marwa.appointment_api.repository;


import com.marwa.appointment_api.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}

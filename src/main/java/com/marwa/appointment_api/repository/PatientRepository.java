package com.marwa.appointment_api.repository;

import com.marwa.appointment_api.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long>{
}

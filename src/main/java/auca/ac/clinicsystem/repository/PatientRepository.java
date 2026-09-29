package auca.ac.clinicsystem.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auca.ac.clinicsystem.domain.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    
    List<Patient> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String search);
}

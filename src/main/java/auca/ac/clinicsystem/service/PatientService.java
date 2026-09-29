package auca.ac.clinicsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.clinicsystem.domain.Patient;
import auca.ac.clinicsystem.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepo;

    public Patient savePatient(Patient patient) {
        return patientRepo.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    public Optional<Patient> getPatientById(UUID id) {
        return patientRepo.findById(id);
    }

    public Optional<Patient> updatePatient(UUID id, Patient patient) {

        return patientRepo.findById(id).map(existingPatient -> {

            existingPatient.setFirstName(patient.getFirstName());
            existingPatient.setLastName(patient.getLastName());
            existingPatient.setDateOfBirth(patient.getDateOfBirth());

            return patientRepo.save(existingPatient);
        });
    }

    public boolean deletePatient(UUID id) {

        if (!patientRepo.existsById(id)) {
            return false;
        }

        patientRepo.deleteById(id);
        return true;
    }
}

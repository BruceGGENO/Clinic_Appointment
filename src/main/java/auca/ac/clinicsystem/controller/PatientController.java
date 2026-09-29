package auca.ac.clinicsystem.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.clinicsystem.domain.Patient;
import auca.ac.clinicsystem.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // CREATE
    @PostMapping("/save")
    public ResponseEntity<Patient> savePatient(
            @RequestBody Patient patient) {

        Patient savedPatient = patientService.savePatient(patient);

        return new ResponseEntity<>(
                savedPatient,
                HttpStatus.CREATED
        );
    }

    // READ ALL
    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatients() {

        return new ResponseEntity<>(
                patientService.getAllPatients(),
                HttpStatus.OK
        );
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable UUID id) {

        return patientService.getPatientById(id)
                .<ResponseEntity<?>>map(patient
                        -> new ResponseEntity<>(patient, HttpStatus.OK))
                .orElse(
                        new ResponseEntity<>(
                                "Patient not found",
                                HttpStatus.NOT_FOUND
                        )
                );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(
            @PathVariable UUID id,
            @RequestBody Patient patient) {

        return patientService.updatePatient(id, patient)
                .<ResponseEntity<?>>map(updatedPatient
                        -> new ResponseEntity<>(
                        updatedPatient,
                        HttpStatus.OK
                ))
                .orElse(
                        new ResponseEntity<>(
                                "Patient not found",
                                HttpStatus.NOT_FOUND
                        )
                );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(
            @PathVariable UUID id) {

        if (patientService.deletePatient(id)) {

            return new ResponseEntity<>(
                    "Patient deleted successfully",
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "Patient not found",
                HttpStatus.NOT_FOUND
        );
    }
}

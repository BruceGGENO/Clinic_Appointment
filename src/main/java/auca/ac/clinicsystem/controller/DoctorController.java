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

import auca.ac.clinicsystem.domain.Doctor;
import auca.ac.clinicsystem.service.DoctorService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/save")
    public ResponseEntity<Doctor> saveDoctor(
            @RequestBody Doctor doctor) {

        Doctor savedDoctor
                = doctorService.saveDoctor(doctor);

        return new ResponseEntity<>(
                savedDoctor,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<Doctor>> getAllDoctors() {

        return new ResponseEntity<>(
                doctorService.getAllDoctors(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctorById(
            @PathVariable UUID id) {

        return doctorService.getDoctorById(id)
                .<ResponseEntity<?>>map(doctor
                        -> new ResponseEntity<>(
                        doctor,
                        HttpStatus.OK
                ))
                .orElse(
                        new ResponseEntity<>(
                                "Doctor not found",
                                HttpStatus.NOT_FOUND
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(
            @PathVariable UUID id,
            @RequestBody Doctor doctor) {

        return doctorService.updateDoctor(id, doctor)
                .<ResponseEntity<?>>map(updatedDoctor
                        -> new ResponseEntity<>(
                        updatedDoctor,
                        HttpStatus.OK
                ))
                .orElse(
                        new ResponseEntity<>(
                                "Doctor not found",
                                HttpStatus.NOT_FOUND
                        )
                );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(
            @PathVariable UUID id) {

        if (doctorService.deleteDoctor(id)) {

            return new ResponseEntity<>(
                    "Doctor deleted successfully",
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "Doctor not found",
                HttpStatus.NOT_FOUND
        );
    }
}

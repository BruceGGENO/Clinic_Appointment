package auca.ac.clinicsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.clinicsystem.domain.Appointment;
import auca.ac.clinicsystem.service.AppointmentService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/save/{patientId}/{doctorId}")
    public ResponseEntity<String> saveAppointment(
            @RequestBody Appointment appointment,
            @PathVariable UUID patientId,
            @PathVariable UUID doctorId) {

        String result = appointmentService.saveAppointment(appointment, patientId, doctorId);
        HttpStatus status = result.equals("Appointment is saved successfully")
                ? HttpStatus.CREATED
                : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(result, status);
    }
}

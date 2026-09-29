package auca.ac.clinicsystem.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.clinicsystem.domain.Appointment;
import auca.ac.clinicsystem.domain.Doctor;
import auca.ac.clinicsystem.domain.Patient;
import auca.ac.clinicsystem.repository.AppointmentRepository;
import auca.ac.clinicsystem.repository.DoctorRepository;
import auca.ac.clinicsystem.repository.PatientRepository;

@Service
public class AppointmentService {

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private AppointmentRepository appointmentRepo;

    public String saveAppointment(Appointment appointment, UUID patientId, UUID doctorId) {
        Optional<Doctor> getDoctor = doctorRepo.findById(doctorId);

        Optional<Patient> getPatient = patientRepo.findById(patientId);

        if (getPatient.isEmpty() || getDoctor.isEmpty()) {
            return "That patient or doctor is not available";
        }

        appointment.setDoctor(getDoctor.get());
        appointment.setPatient(getPatient.get());
        appointmentRepo.save(appointment);

        return "Appointment is saved successfully";
    }
}

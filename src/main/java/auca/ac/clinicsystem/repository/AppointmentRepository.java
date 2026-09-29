package auca.ac.clinicsystem.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import auca.ac.clinicsystem.domain.Appointment;
import auca.ac.clinicsystem.domain.Doctor;
import auca.ac.clinicsystem.domain.Patient;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    List<Appointment> findByDoctor(Doctor doctor);

    List<Appointment> findByPatient(Patient patient);

    @Query("SELECT a FROM Appointment a WHERE a.patient.firstName = : firstName")
    List<Appointment> findByFirstName(@Param("firstName") String fName);

    @Query("SELECT a Appointment WHERE a.patient.location.countryName = : name")
    List<Appointment> findByCountryName(@Param("name") String name);

}

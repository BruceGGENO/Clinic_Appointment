package auca.ac.clinicsystem.repository;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auca.ac.clinicsystem.domain.Doctor;
import auca.ac.clinicsystem.domain.Office;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    Optional<Doctor> findByFirstNameAndLastNameAndDateOfBirth(String firstName, String lastName, Date dateOfBirth);

    Boolean existsByFirstNameAndLastNameAndDateOfBirth(String firstName, String lastName, Date dateOfBirth);


    List<Doctor> findByOffice(Office office);

    Boolean existsByOffice(Office office);

    Boolean existsByOfficeAndIdNot(Office office, UUID id);

    List<Doctor> findByFirstName(String fname);
    
}

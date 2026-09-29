package auca.ac.clinicsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import auca.ac.clinicsystem.domain.Doctor;
import auca.ac.clinicsystem.domain.Office;
import auca.ac.clinicsystem.repository.DoctorRepository;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepo;

    public DoctorService(DoctorRepository doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    public Doctor saveDoctor(Doctor doctor) {

        Optional<Doctor> doctorExist = doctorRepo.findByFirstNameAndLastNameAndDateOfBirth(doctor.getFirstName(), doctor.getLastName(), doctor.getDateOfBirth());

        if (doctorExist.isPresent()) {
            throw new Error("Doctor already exists");
        }

        if (doctorRepo.existsByOffice(doctor.getOffice())) {
            throw new Error("Office is occupiedr");
        }

        return doctorRepo.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    public Optional<Doctor> getDoctorById(UUID id) {
        return doctorRepo.findById(id);
    }

    public Optional<Doctor> updateDoctor(
            UUID id,
            Doctor doctor) {

        return doctorRepo.findById(id).map(existingDoctor -> {

            existingDoctor.setFirstName(
                    doctor.getFirstName()
            );

            existingDoctor.setLastName(
                    doctor.getLastName()
            );

            existingDoctor.setDateOfBirth(
                    doctor.getDateOfBirth()
            );

            Office office = doctor.getOffice();
            if (office != null && doctorRepo.existsByOfficeAndIdNot(office, id)) {
                throw new IllegalArgumentException("Office is already occupied by another doctor");
            }
            existingDoctor.setOffice(office);

            return doctorRepo.save(existingDoctor);
        });
    }

    public boolean deleteDoctor(UUID id) {

        if (!doctorRepo.existsById(id)) {
            return false;
        }

        doctorRepo.deleteById(id);

        return true;
    }
}

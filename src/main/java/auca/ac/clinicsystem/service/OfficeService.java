package auca.ac.clinicsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.clinicsystem.domain.Office;
import auca.ac.clinicsystem.repository.OfficeRepository;

@Service
public class OfficeService {

    @Autowired
    private OfficeRepository offRepo;

    public String saveOffice(Office office) {
        Optional<Office> checkOffice = offRepo.findByOfficeNumber(office.getOfficeNumber());
        if (checkOffice.isPresent()) {
            return "Office already exists";

        };
        offRepo.save(office);
        return "Office is saved successfully";
    }

    public List<Office> getAllOffices() {
        return offRepo.findAll();
    }

    public Optional<Office> getOfficeById(UUID id) {
        return offRepo.findById(id);
    }

    public String updateOffice(UUID id, Office office) {

        Optional<Office> existingOffice = offRepo.findById(id);

        if (existingOffice.isEmpty()) {
            return "Office not found";
        }

        Office officeToUpdate = existingOffice.get();

        officeToUpdate.setName(office.getName());
        officeToUpdate.setOfficeNumber(office.getOfficeNumber());

        offRepo.save(officeToUpdate);

        return "Office updated successfully";
    }

    public String deleteOffice(UUID id) {

        Optional<Office> existingOffice = offRepo.findById(id);

        if (existingOffice.isEmpty()) {
            return "Office not found";
        }

        offRepo.deleteById(id);

        return "Office deleted successfully";
    }

}

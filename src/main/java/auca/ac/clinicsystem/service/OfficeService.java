package auca.ac.clinicsystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.clinicsystem.domain.Office;
import auca.ac.clinicsystem.repository.OfficeRepository;

@Service 
public class OfficeService {

    @Autowired 
    private OfficeRepository offRepo;


    

    public String saveOffice(Office office){
        Optional<Office> checkOffice = offRepo.findByOfficeNumber(office.getOfficeNumber());
        if(checkOffice.isPresent()){
            return "Office already exists";
            
        };
        offRepo.save(office);
        return "Office is saved successfully";
    }
}

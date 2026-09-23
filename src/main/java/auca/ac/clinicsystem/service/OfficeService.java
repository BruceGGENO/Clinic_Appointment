package kigali.clinic.rw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.clinic.rw.domain.Office;
import kigali.clinic.rw.repository.OfficeRepository;
import java.util.*;

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

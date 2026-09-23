package auca.ac.clinicsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.clinicsystem.domain.Office;
import auca.ac.clinicsystem.service.OfficeService;

@RestController
@RequestMapping(value = "/api/office")
public class OfficeController {

    @Autowired
    private OfficeService offServe;

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveOffice(@RequestBody Office office) {
        String returnedMessage = offServe.saveOffice(office);

        if (returnedMessage.equals("Office is saved successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }

    }

}

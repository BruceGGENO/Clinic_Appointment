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

    @GetMapping("/all")
    public ResponseEntity<List<Office>> getAllOffices() {

        List<Office> offices = offServe.getAllOffices();

        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOfficeById(@PathVariable UUID id) {

        return offServe.getOfficeById(id)
                .<ResponseEntity<?>>map(office -> new ResponseEntity<>(office, HttpStatus.OK))
                .orElse(new ResponseEntity<>("Office not found", HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOffice(
            @PathVariable UUID id,
            @RequestBody Office office) {

        String returnedMessage = offServe.updateOffice(id, office);

        if (returnedMessage.equals("Office updated successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOffice(@PathVariable UUID id) {

        String returnedMessage = offServe.deleteOffice(id);

        if (returnedMessage.equals("Office deleted successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }

}

package auca.ac.clinicsystem.domain;

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Location {

    private UUID id;
    private String countryName;
    private String districtName;

    @OneToMany(mappedBy = "location")
    List<Patient> patients;

}

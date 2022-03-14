package me.anass.association.controller;

import me.anass.association.entity.Patient;
import me.anass.association.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientConstroller {

    PatientRepository patientRepository;

    @Autowired
    public PatientConstroller(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patients")
    public List<Patient> getPatients(){
        return patientRepository.findAll();
    }
}

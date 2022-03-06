package me.anass.jpa;

import me.anass.jpa.entity.Patient;
import me.anass.jpa.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class Application implements CommandLineRunner {

    PatientRepository patientRepository;

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.saveAll(Arrays.asList(
                new Patient(null, "Arrhioui", new Date(), false, 1),
                new Patient(null, "Ofkir", new Date(), true, 10000),
                new Patient(null, "Med", new Date(), false, 10)
        ));

        for (int i=0; i<100; i++)
            patientRepository.save(new Patient(null, "Arrhioui", new Date(), Math.random() > .5, (int)(Math.random()*100)));
        System.out.println("======================================");
        Page<Patient> patientPage = patientRepository.findAll(PageRequest.of(0, 5));
        patientPage.forEach(System.out::println);
        System.out.println("patientPage.getTotalElements() = " + patientPage.getTotalElements());
        System.out.println("patientPage.getNumber() = " + patientPage.getNumber());
        System.out.println("patientPage.getTotalPages() = " + patientPage.getTotalPages());
        List<Patient> content = patientPage.getContent();
        content.forEach(System.out::println);
        System.out.println("======================================");
        Patient patient = patientRepository.findById(1L).orElse(null);
        if(!Objects.isNull(patient)){
            System.out.println("patient = " + patient);
        }
        patient.setScore(123);
        patientRepository.save(patient);

        System.out.println("malade =======================================================");
        patientRepository.findAllByMalade(true, PageRequest.of(0, 10)).forEach(System.out::println);
    }
}

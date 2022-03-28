package me.arrhioui.springmvc;

import me.arrhioui.springmvc.entity.Patient;
import me.arrhioui.springmvc.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //@Bean
    CommandLineRunner runner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null, "Hassan", new Date(),true, 5));
            patientRepository.save(new Patient(null, "Mohammed", new Date(),true, 5));
            patientRepository.save(new Patient(null, "Yasmine", new Date(),true, 5));
            patientRepository.save(new Patient(null, "Hanan", new Date(),true, 5));

            patientRepository.findAll().forEach(p -> {
                System.out.println(p.getNom());
            });
        };
    }

}

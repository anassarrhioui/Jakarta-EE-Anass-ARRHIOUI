package me.anass.association;

import me.anass.association.entity.*;
import me.anass.association.repository.ConsultationRepository;
import me.anass.association.repository.MedecinRepository;
import me.anass.association.repository.PatientRepository;
import me.anass.association.repository.RendezVousRepository;
import me.anass.association.service.IHospitalService;
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
    @Bean
    CommandLineRunner start(IHospitalService hospitalService,
                            PatientRepository patientRepository,
                            MedecinRepository medecinRepository){
        return args -> {
            Stream.of("Ayman", "Hannas", "Hassnae")
                    .forEach( nom -> {
                        Medecin medecin = new Medecin();
                        medecin.setNom(nom);
                        medecin.setEmail(nom+"@gmail.com");
                        medecin.setSpecialite(Math.random() > .5 ? "Cardio" : "Dentiste");
                        hospitalService.save(medecin);
                    });

            Stream.of("Mohammed", "Hassan", "Najat")
                    .forEach( nom -> {
                        Patient patient = new Patient();
                        patient.setNom(nom);
                        patient.setDateNaissance(new Date());
                        patient.setMalade(Math.random() >= .5);
                        hospitalService.save(patient);
                    });
            Patient patient1 = patientRepository.findById(1L).orElse(null);
            Patient patient2 = patientRepository.findByNom("Hassan");

            Medecin medecin = medecinRepository.findByNom("Hassnae");

            RendezVous rendezVous = new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRendeVous.PENDING);
            rendezVous.setPatient(patient1);
            rendezVous.setMedecin(medecin);
            RendezVous rendezVousReturned = hospitalService.save(rendezVous);

            Consultation consultation = new Consultation();
            consultation.setDate(new Date());
            consultation.setRendezVous(rendezVousReturned);
            consultation.setRapport("Hello world");
            hospitalService.save(consultation);

        };
    }

}

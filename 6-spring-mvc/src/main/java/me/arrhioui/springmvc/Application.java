package me.arrhioui.springmvc;

import me.arrhioui.springmvc.repository.PatientRepository;
import me.arrhioui.springmvc.service.SecurityService2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner runner(
            PatientRepository patientRepository,
            SecurityService2 securityService2
    ){
        return args -> {
            //patientRepository.save(new Patient(null, "Hassan", new Date(),true, 5));
            //patientRepository.save(new Patient(null, "Mohammed", new Date(),true, 5));
            //patientRepository.save(new Patient(null, "Yasmine", new Date(),true, 5));
            //patientRepository.save(new Patient(null, "Hanan", new Date(),true, 5));

            //patientRepository.findAll().forEach(p -> {
             //   System.out.println(p.getNom());
            //});
            securityService2.saveUser("user1", "1234", "1234");
            securityService2.saveUser("user2", "1234", "1234");
            securityService2.saveUser("user3", "1234", "1234");

            securityService2.saveRole("USER", "");
            securityService2.saveRole("ADMIN", "");

            /*securityService2.addRoleToUser("user1", "USER");
            securityService2.addRoleToUser("user1", "ADMIN");
            securityService2.addRoleToUser("user2", "USER");
            securityService2.addRoleToUser("user3", "USER");*/

        };
    }

}

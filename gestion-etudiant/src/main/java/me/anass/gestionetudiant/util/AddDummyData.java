package me.anass.gestionetudiant.util;

import me.anass.gestionetudiant.entity.Student;
import me.anass.gestionetudiant.enumeration.GenreEnum;
import me.anass.gestionetudiant.repository.StudentRepository;
import me.anass.gestionetudiant.security.entity.AppRole;
import me.anass.gestionetudiant.security.entity.AppUser;
import me.anass.gestionetudiant.security.repository.AppRoleRepository;
import me.anass.gestionetudiant.security.repository.AppUserRepository;
import me.anass.gestionetudiant.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Configuration
public class AddDummyData {

    @Bean
    public CommandLineRunner runner(
            StudentRepository studentRepository,
            AppRoleRepository appRoleRepository,
            AppUserRepository appUserRepository,
            SecurityService securityService,
            PasswordEncoder passwordEncoder
    ){
        return args -> {

            securityService.saveUser("admin", "1234", "1234");
            securityService.saveUser("user2", "1234", "1234");
            securityService.saveUser("user3", "1234", "1234");

            securityService.saveRole("USER", "");
            securityService.saveRole("ADMIN", "");

            securityService.addRoleToUser("admin", "USER");
            securityService.addRoleToUser("admin", "ADMIN");
            securityService.addRoleToUser("user2", "USER");
            securityService.addRoleToUser("user3", "USER");
            studentRepository.saveAll(
                    Arrays.asList(
                            Student.builder().nom("Anass").prenom("Arrhioui").dateNaissance(new Date()).enRegle(false).genre(GenreEnum.M).email("Anass@gmail.com").build(),
                            Student.builder().nom("Ayman").prenom("Arrhioui").dateNaissance(new Date()).enRegle(false).genre(GenreEnum.M).email("Anass@gmail.com").build(),
                            Student.builder().nom("Mohammed").prenom("Arrhioui").dateNaissance(new Date()).enRegle(false).genre(GenreEnum.M).email("Anass@gmail.com").build(),
                            Student.builder().nom("Aouar").prenom("Arrhioui").dateNaissance(new Date()).enRegle(false).genre(GenreEnum.M).email("Anass@gmail.com").build(),
                            Student.builder().nom("Mohammed3").prenom("Arrhioui").dateNaissance(new Date()).enRegle(false).genre(GenreEnum.M).email("Anass@gmail.com").build(),
                            Student.builder().nom("Imad").prenom("Arrhioui").dateNaissance(new Date()).enRegle(false).genre(GenreEnum.M).email("Anass@gmail.com").build(),
                            Student.builder().nom("Hassan").prenom("Arrhioui").dateNaissance(new Date()).enRegle(false).genre(GenreEnum.M).email("Anass@gmail.com").build(),
                            Student.builder().nom("Rim").prenom("Arrhioui").dateNaissance(new Date()).enRegle(false).genre(GenreEnum.M).email("Anass@gmail.com").build(),
                            Student.builder().nom("Akram").prenom("Arrhioui").dateNaissance(new Date()).enRegle(false).genre(GenreEnum.M).email("Anass@gmail.com").build(),
                            Student.builder().nom("Tariq").prenom("Arrhioui").dateNaissance(new Date()).enRegle(false).genre(GenreEnum.M).email("Anass@gmail.com").build(),
                            Student.builder().nom("yassine").prenom("Arrhioui").dateNaissance(new Date()).enRegle(false).genre(GenreEnum.M).email("Anass@gmail.com").build(),
                            Student.builder().nom("Mohammed10").prenom("Arrhioui").dateNaissance(new Date()).enRegle(false).genre(GenreEnum.M).email("Anass@gmail.com").build(),
                            Student.builder().nom("Mohammed11").prenom("Arrhioui").dateNaissance(new Date()).enRegle(false).genre(GenreEnum.M).email("Anass@gmail.com").build(),
                            Student.builder().nom("Mohammed12").prenom("Arrhioui").dateNaissance(new Date()).enRegle(false).genre(GenreEnum.M).email("Anass@gmail.com").build(),
                            Student.builder().nom("Mohammed13").prenom("Arrhioui").dateNaissance(new Date()).enRegle(false).genre(GenreEnum.M).email("Anass@gmail.com").build(),
                            Student.builder().nom("Mohammed14").prenom("Arrhioui").dateNaissance(new Date()).enRegle(false).genre(GenreEnum.M).email("Anass@gmail.com").build()
                    )
            );
        };
    }
}

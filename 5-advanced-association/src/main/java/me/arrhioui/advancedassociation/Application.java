package me.arrhioui.advancedassociation;

import me.arrhioui.advancedassociation.entity.Role;
import me.arrhioui.advancedassociation.entity.User;
import me.arrhioui.advancedassociation.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner runner(UserService userService){
        return args -> {
            User user1 = new User();
            user1.setUsername("user1");
            user1.setPassword("user1pass");
            userService.saveUser(user1);

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("adminpass");
            userService.saveUser(admin);

            Stream.of("STUDENT", "USER", "ADMIN")
                    .forEach(r -> {
                        Role role = new Role();
                        role.setRoleName(r);
                        userService.saveRole(role);
                    });

            userService.addRoleToUser("user1", "STUDENT");
            userService.addRoleToUser("user1", "USER");
            userService.addRoleToUser("admin", "USER");
            userService.addRoleToUser("admin", "ADMIN");

        };
    }

}

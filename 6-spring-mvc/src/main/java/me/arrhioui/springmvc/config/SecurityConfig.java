package me.arrhioui.springmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        System.out.println("passwordEncoder.encode(\"1234\") = " + passwordEncoder.encode("1234"));
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .and()
                .withUser("user2")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder.encode("1234"))
                .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/").
                permitAll();

        http.authorizeRequests()
                .antMatchers("/delete/**", "/edit/**", "/save/**", "/formPatient/**")
                .hasRole("ADMIN");

        http.authorizeRequests()
                        .antMatchers("/index/**")
                        .hasRole("USER");

        http.authorizeRequests()
                .anyRequest()
                .authenticated();

        http.exceptionHandling()
                .accessDeniedPage("/403");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

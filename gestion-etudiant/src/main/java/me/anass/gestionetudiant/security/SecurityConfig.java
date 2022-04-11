package me.anass.gestionetudiant.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    DataSource dataSource;
    UserDetailsService userDetailsService;
    PasswordEncoder passwordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/").
                permitAll();

        http.authorizeRequests()
                .antMatchers("/delete/**", "/formAdd/**", "/formEdit/**", "/addStudent/**")
                .hasAuthority("ADMIN");

        http.authorizeRequests()
                .antMatchers("/index/**")
                .hasAuthority("USER");

        http.authorizeRequests()
                .anyRequest()
                .authenticated();
    }


}

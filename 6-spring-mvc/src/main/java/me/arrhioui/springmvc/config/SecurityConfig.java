package me.arrhioui.springmvc.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

        passwordEncoder.encode("1234");
        /*System.out.println("passwordEncoder.encode(\"1234\") = " + passwordEncoder.encode("1234"));
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
                .roles("USER", "ADMIN");*/

        /*auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username as principal, password as credentials, active from users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username as principal, role as role from users_roles WHERE username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder());*/

        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/").
                permitAll();

        http.authorizeRequests()
                .antMatchers("/delete/**", "/edit/**", "/save/**", "/formPatient/**")
                .hasAuthority("ADMIN");

        http.authorizeRequests()
                .antMatchers("/index/**")
                .hasAuthority("USER");

        http.authorizeRequests()
                .anyRequest()
                .authenticated();

        http.exceptionHandling()
                .accessDeniedPage("/403");
    }


}

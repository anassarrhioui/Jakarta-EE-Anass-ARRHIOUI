package me.anass.gestionetudiant.security.service;

import lombok.AllArgsConstructor;
import me.anass.gestionetudiant.security.entity.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private SecurityService securityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = securityService.loadAppUserByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();

        appUser.getRoles().forEach( role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));

        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }
}

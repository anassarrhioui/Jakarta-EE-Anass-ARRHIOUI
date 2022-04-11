package me.arrhioui.springmvc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.arrhioui.springmvc.entity.AppRole;
import me.arrhioui.springmvc.entity.AppUser;
import me.arrhioui.springmvc.repository.AppRoleRepository;
import me.arrhioui.springmvc.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class SecurityServiceImpl2 implements SecurityService2 {

    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword))
            throw new RuntimeException("Passwords does not match");

        String encryptedPassword = passwordEncoder.encode(password);
        AppUser appUser = new AppUser();
        appUser.setId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(encryptedPassword);
        appUser.setActive(true);
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole saveRole(String roleName, String description) {
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole != null){
            throw new RuntimeException("");
        }
        appRole = new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String usename, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(usename);
        if (appUser == null){
            throw new RuntimeException("User not found");
        }
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole == null){
            throw new RuntimeException("Role not found");
        }
        appUser.getRoles().add(appRole);
    }

    @Override
    public void removeRoleToUser(String usename, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(usename);
        if (appUser == null){
            throw new RuntimeException("User not found");
        }
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole == null){
            throw new RuntimeException("Role not found");
        }
        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadAppUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}

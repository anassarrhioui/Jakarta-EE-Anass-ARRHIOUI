package me.anass.gestionetudiant.security.service;


import me.anass.gestionetudiant.security.entity.AppRole;
import me.anass.gestionetudiant.security.entity.AppUser;

public interface SecurityService {
    AppUser saveUser(String username, String password, String rePassword);
    AppRole saveRole(String roleName, String description);
    void addRoleToUser(String usename, String roleName);
    void removeRoleToUser(String usename, String roleName);
    AppUser loadAppUserByUsername(String username);
}

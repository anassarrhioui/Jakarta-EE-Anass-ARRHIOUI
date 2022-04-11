package me.arrhioui.springmvc.service;

import me.arrhioui.springmvc.entity.AppRole;
import me.arrhioui.springmvc.entity.AppUser;

public interface SecurityService2 {
    AppUser saveUser(String username, String password, String rePassword);
    AppRole saveRole(String roleName, String description);
    void addRoleToUser(String usename, String roleName);
    void removeRoleToUser(String usename, String roleName);
    AppUser loadAppUserByUsername(String username);
}

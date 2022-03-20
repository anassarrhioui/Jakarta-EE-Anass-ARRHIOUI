package me.arrhioui.advancedassociation.service;

import me.arrhioui.advancedassociation.entity.Role;
import me.arrhioui.advancedassociation.entity.User;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    User findUserByUsername(String username);
    Role findRoleByRolename(String rolename);
    void addRoleToUser(String username, String role);
    User authenticate(String username, String password);
}

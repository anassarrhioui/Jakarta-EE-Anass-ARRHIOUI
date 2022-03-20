package me.arrhioui.advancedassociation.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.AllArgsConstructor;
import me.arrhioui.advancedassociation.entity.Role;
import me.arrhioui.advancedassociation.entity.User;
import me.arrhioui.advancedassociation.repository.RoleRepository;
import me.arrhioui.advancedassociation.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    final private UserRepository userRepository;
    final private RoleRepository roleRepository;


    @Override
    public User saveUser(User user) {
        user.setId(UUID.randomUUID().toString());
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(bcryptHashString);
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role findRoleByRolename(String rolename) {
        return roleRepository.findByRoleName(rolename);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        User user = findUserByUsername(username);
        Role role = findRoleByRolename(rolename);
        if(!Objects.isNull(user.getRoles())){
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
    }

    @Override
    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        return result.verified ? user : null;
    }
}

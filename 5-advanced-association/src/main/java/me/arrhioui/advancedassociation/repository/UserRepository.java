package me.arrhioui.advancedassociation.repository;

import me.arrhioui.advancedassociation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

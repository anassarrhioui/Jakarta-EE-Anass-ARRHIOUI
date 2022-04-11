package me.anass.gestionetudiant.security.repository;

import me.anass.gestionetudiant.security.entity.AppRole;
import me.anass.gestionetudiant.security.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}

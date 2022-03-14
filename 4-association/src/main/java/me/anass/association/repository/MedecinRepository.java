package me.anass.association.repository;

import me.anass.association.entity.Medecin;
import me.anass.association.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Medecin findByNom(String nom);
}

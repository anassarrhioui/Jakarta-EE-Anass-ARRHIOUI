package me.anass.association.repository;

import me.anass.association.entity.Patient;
import me.anass.association.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendezVousRepository extends JpaRepository<RendezVous, String> {
}

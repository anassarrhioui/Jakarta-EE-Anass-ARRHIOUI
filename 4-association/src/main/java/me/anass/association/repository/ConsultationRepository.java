package me.anass.association.repository;

import me.anass.association.entity.Consultation;
import me.anass.association.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}

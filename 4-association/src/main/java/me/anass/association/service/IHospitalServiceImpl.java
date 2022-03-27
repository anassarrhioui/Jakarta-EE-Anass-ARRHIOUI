package me.anass.association.service;

import lombok.AllArgsConstructor;
import me.anass.association.entity.Consultation;
import me.anass.association.entity.Medecin;
import me.anass.association.entity.Patient;
import me.anass.association.entity.RendezVous;
import me.anass.association.repository.ConsultationRepository;
import me.anass.association.repository.MedecinRepository;
import me.anass.association.repository.PatientRepository;
import me.anass.association.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class IHospitalServiceImpl implements IHospitalService {

    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;
    private final RendezVousRepository rendezVousRepository;
    private final ConsultationRepository consultationRepository;

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin save(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous save(RendezVous rendezVous) {
        rendezVous.setId(UUID.randomUUID().toString());
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation save(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}

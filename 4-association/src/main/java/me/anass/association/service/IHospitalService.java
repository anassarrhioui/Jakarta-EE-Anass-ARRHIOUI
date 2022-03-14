package me.anass.association.service;

import me.anass.association.entity.Consultation;
import me.anass.association.entity.Medecin;
import me.anass.association.entity.Patient;
import me.anass.association.entity.RendezVous;

public interface IHospitalService {

    Patient save(Patient patient);

    Medecin save(Medecin medecin);

    RendezVous save(RendezVous rendezVous);

    Consultation save(Consultation consultation);
}

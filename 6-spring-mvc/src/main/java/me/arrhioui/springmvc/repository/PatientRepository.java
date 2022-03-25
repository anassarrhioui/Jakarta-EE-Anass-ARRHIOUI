package me.arrhioui.springmvc.repository;

import me.arrhioui.springmvc.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNomContainsOrderByIdDesc(String kw, Pageable pageable);
}

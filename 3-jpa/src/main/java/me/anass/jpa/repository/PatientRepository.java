package me.anass.jpa.repository;

import me.anass.jpa.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllByMaladeIsTrue();
    Page<Patient> findAllByMalade(Boolean malade, Pageable pageable);
    Page<Patient> findAllByMaladeAndScoreLessThan(boolean malade, int score, Pageable pageable);
    Page<Patient> findByDateNaisanceBetweenAndMaladeIsTrueOrNomLike(Data start, Data end,String keyWord, Pageable pageable);
    @Query("SELECT p from Patient p where p.dateNaisance between :x and :y or p.nom like :z")
    Page<Patient> chercher(@Param("x") Date d1, @Param("y") Date d2, @Param("z") String keyWord, Pageable pageable);
}

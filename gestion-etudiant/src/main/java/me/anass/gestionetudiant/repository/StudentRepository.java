package me.anass.gestionetudiant.repository;

import me.anass.gestionetudiant.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByNom(String string);
    Page<Student> findByNomContainsIgnoreCaseOrPrenomContainsIgnoreCaseOrderByIdDesc(String nom, String prenom, Pageable pageable);
}

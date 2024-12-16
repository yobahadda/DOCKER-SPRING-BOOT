package com.example.test.repository;
import com.example.test.model.AffectationProfesseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AffectationProfesseurRepository extends JpaRepository<AffectationProfesseur, Long> {
    @Query("SELECT a.elementDeModule.module.id FROM AffectationProfesseur a WHERE a.professeur.id = :professorId")
    List<Long> findElementIdsByProfessorId(@Param("professorId") Long professorId);
}

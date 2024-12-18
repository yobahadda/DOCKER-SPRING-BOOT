package com.example.test.repository;

import com.example.test.model.AffectationProfesseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffectationProfesseurRepository extends JpaRepository<AffectationProfesseur, Long> {

    List<AffectationProfesseur> findAllByProfesseurId(Long professeurId);

    AffectationProfesseur findByProfesseurIdAndElementDeModuleId(Long profId, Long eleId);
}
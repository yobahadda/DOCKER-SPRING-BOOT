package com.example.test.repository;
import com.example.test.model.AffectationProfesseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AffectationProfesseurRepository extends JpaRepository<AffectationProfesseur, Long> {}
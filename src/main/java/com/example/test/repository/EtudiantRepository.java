package com.example.test.repository;
import com.example.test.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {}
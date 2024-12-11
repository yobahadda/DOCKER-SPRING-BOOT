package com.example.test.repository;
import com.example.test.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {}
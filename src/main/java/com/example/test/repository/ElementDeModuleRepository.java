package com.example.test.repository;


import com.example.test.model.ElementDeModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ElementDeModuleRepository extends JpaRepository<ElementDeModule, Long> {

    Optional<ElementDeModule> findById(Long id);
}

package com.example.test.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.test.model.ElementDeModule;
@Repository
public interface ElementDeModuleRepository extends JpaRepository<ElementDeModule, Long> {}

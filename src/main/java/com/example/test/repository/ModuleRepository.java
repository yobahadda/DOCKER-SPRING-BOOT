package com.example.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.test.model.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {}
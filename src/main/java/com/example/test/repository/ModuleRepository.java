package com.example.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.test.model.Module;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    @Query("SELECT DISTINCT m FROM Module m JOIN ElementDeModule e ON m.id = e.module.id WHERE e.id IN :elementIds")
    List<Module> findModulesByElementIds(@Param("elementIds") List<Long> elementIds);
}

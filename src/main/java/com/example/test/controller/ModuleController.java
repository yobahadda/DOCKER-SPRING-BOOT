package com.example.test.controller;
import com.example.test.repository.ModuleRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.example.test.model.Module;

// Module Controller
@RestController
@RequestMapping("/modules")
public class ModuleController {
    @Autowired
    private ModuleRepository repository;

    @GetMapping
    public List<Module> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Module create(@RequestBody Module module) {
        return repository.save(module);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Module> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Module> update(@PathVariable Long id, @RequestBody Module updated) {
        return repository.findById(id).map(existing -> {
            existing.setNom(updated.getNom());
            existing.setFiliere(updated.getFiliere());
            existing.setSemestre(updated.getSemestre());
            return ResponseEntity.ok(repository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return repository.findById(id).map(existing -> {
            repository.delete(existing);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElse(ResponseEntity.notFound().build());
    }
}
package com.example.test.controller;
import com.example.test.repository.FiliereRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.example.test.model.Filiere;
@RestController
@RequestMapping("/filieres")
public class FiliereController {
    @Autowired
    private FiliereRepository repository;

    @GetMapping
    public List<Filiere> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Filiere create(@RequestBody Filiere filiere) {
        return repository.save(filiere);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filiere> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filiere> update(@PathVariable Long id, @RequestBody Filiere updated) {
        return repository.findById(id).map(existing -> {
            existing.setNom(updated.getNom());
            existing.setDescription(updated.getDescription());
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
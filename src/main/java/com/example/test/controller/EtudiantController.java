package com.example.test.controller;
import com.example.test.repository.EtudiantRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.example.test.model.Etudiant;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {
    @Autowired
    private EtudiantRepository repository;

    @GetMapping
    public List<Etudiant> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Etudiant create(@RequestBody Etudiant etudiant) {
        return repository.save(etudiant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> update(@PathVariable Long id, @RequestBody Etudiant updated) {
        return repository.findById(id).map(existing -> {
            existing.setNom(updated.getNom());
            existing.setPrenom(updated.getPrenom());
            existing.setFiliere(updated.getFiliere());
            existing.setImageUrl(updated.getImageUrl());
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
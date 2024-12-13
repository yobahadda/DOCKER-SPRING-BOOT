package com.example.test.controller;

import com.example.test.repository.ProfesseurRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.example.test.model.Professeur;

@RestController
@RequestMapping("/professeurs")
public class ProfesseurController {
    @Autowired
    private ProfesseurRepository repository;

    @GetMapping
    public List<Professeur> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Professeur create(@RequestBody Professeur professeur) {
        return repository.save(professeur);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professeur> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-login/{login}")
    public ResponseEntity<Professeur> findByLogin(@PathVariable String login) {
        return repository.findByLogin(login).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professeur> update(@PathVariable Long id, @RequestBody Professeur updated) {
        return repository.findById(id).map(existing -> {
            existing.setNom(updated.getNom());
            existing.setPrenom(updated.getPrenom());
            existing.setSpecialite(updated.getSpecialite());
            existing.setCodeIdentification(updated.getCodeIdentification());
            existing.setLogin(updated.getLogin());
            existing.setMotDePasse(updated.getMotDePasse());
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
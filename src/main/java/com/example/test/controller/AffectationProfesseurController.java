package com.example.test.controller;
import com.example.test.repository.AffectationProfesseurRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.example.test.model.AffectationProfesseur;
import com.example.test.model.Professeur;
import com.example.test.controller.PersonController;
import com.example.test.repository.ProfesseurRepository;
@RestController
@RequestMapping("/affectationsProfesseurs")
public class AffectationProfesseurController {
    @Autowired
    private AffectationProfesseurRepository repository;

    @GetMapping
    public List<AffectationProfesseur> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public AffectationProfesseur create(@RequestBody AffectationProfesseur affectation) {
        return repository.save(affectation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AffectationProfesseur> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AffectationProfesseur> update(@PathVariable Long id, @RequestBody AffectationProfesseur updated) {
        return repository.findById(id).map(existing -> {
            existing.setProfesseur(updated.getProfesseur());
            existing.setElementDeModule(updated.getElementDeModule());
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
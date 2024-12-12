package com.example.test.controller;
import com.example.test.repository.UtilisateurRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.example.test.model.Utilisateur;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {
    @Autowired
    private UtilisateurRepository repository;

    @GetMapping
    public List<Utilisateur> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Utilisateur create(@RequestBody Utilisateur utilisateur) {
        return repository.save(utilisateur);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> update(@PathVariable Long id, @RequestBody Utilisateur updated) {
        return repository.findById(id).map(existing -> {
            existing.setLogin(updated.getLogin());
            existing.setMotDePasse(updated.getMotDePasse());
            existing.setRole(updated.getRole());
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

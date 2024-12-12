package com.example.test.controller;
import com.example.test.repository.ElementDeModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.example.test.model.ElementDeModule;
@RestController
@RequestMapping("/elementsDeModule")
public class ElementDeModuleController {
    @Autowired
    private ElementDeModuleRepository repository;

    @GetMapping
    public List<ElementDeModule> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ElementDeModule create(@RequestBody ElementDeModule elementDeModule) {
        return repository.save(elementDeModule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElementDeModule> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ElementDeModule> update(@PathVariable Long id, @RequestBody ElementDeModule updated) {
        return repository.findById(id).map(existing -> {
            existing.setNom(updated.getNom());
            existing.setModule(updated.getModule());
            existing.setCoefficient(updated.getCoefficient());
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

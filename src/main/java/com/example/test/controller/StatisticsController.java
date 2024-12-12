package com.example.test.controller;
import com.example.test.model.Module;
import com.example.test.repository.NoteRepository;
import com.example.test.repository.ModuleRepository;
import com.example.test.repository.EtudiantRepository;
import com.example.test.model.Etudiant;
import java.util.Optional;
import com.example.test.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/etudiant/{etudiantId}/module/{moduleId}")
    public ResponseEntity<?> calculateStats(@PathVariable Long etudiantId, @PathVariable Long moduleId) {
        Optional<Etudiant> etudiantOpt = etudiantRepository.findById(etudiantId);
        Optional<Module> moduleOpt = moduleRepository.findById(moduleId);

        if (etudiantOpt.isPresent() && moduleOpt.isPresent()) {
            Etudiant etudiant = etudiantOpt.get();
            Module module = moduleOpt.get();


            List<Note> notes = noteRepository.findAll().stream()
                    .filter(note -> note.getEtudiant().equals(etudiant) && note.getElementDeModule().getModule().equals(module))
                    .collect(Collectors.toList());

            if (notes.isEmpty()) {
                return ResponseEntity.ok("No notes found for the specified Etudiant and Module.");
            }

            // Calculate statistics
            double average = notes.stream().mapToInt(Note::getNote).average().orElse(0);
            int max = notes.stream().mapToInt(Note::getNote).max().orElse(0);
            int min = notes.stream().mapToInt(Note::getNote).min().orElse(0);

            Map<String, Object> stats = new HashMap<>();
            stats.put("Etudiant", etudiant.getNom() + " " + etudiant.getPrenom());
            stats.put("Module", module.getNom());
            stats.put("Average", average);
            stats.put("Max", max);
            stats.put("Min", min);

            return ResponseEntity.ok(stats);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Etudiant or Module not found.");
    }
}

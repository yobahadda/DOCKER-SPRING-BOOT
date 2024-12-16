package com.example.test.controller;
import com.example.test.model.Note;
import com.example.test.repository.ElementDeModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.example.test.model.ElementDeModule;
import com.example.test.repository.NoteRepository;
@RestController
@RequestMapping("/elementsDeModule")
public class ElementDeModuleController {
    @Autowired
    private ElementDeModuleRepository repository;
    @Autowired
    private NoteRepository Noterepository;

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
    @GetMapping("/{elementId}/export")
    public ResponseEntity<?> exportElementGrades(@PathVariable Long elementId, @RequestParam String format) {
        List<Note> grades = Noterepository.findByElementId(elementId);

        if (grades.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No grades found for the specified element.");
        }

        StringBuilder exportData = new StringBuilder();
        exportData.append("Student Name, Grade, Status\n");

        grades.forEach(note -> {
            String status = note.isValide() ? "Valid" : "Invalid";
            exportData.append(note.getEtudiant().getNom())
                    .append(" ")
                    .append(note.getEtudiant().getPrenom())
                    .append(",")
                    .append(note.getNote())
                    .append(",")
                    .append(status)
                    .append("\n");
        });

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=grades.csv");
        return ResponseEntity.ok()
                .headers(headers)
                .body(exportData.toString());
    }
    @GetMapping("/{elementId}/status")
    public ResponseEntity<?> fetchElementStatus(@PathVariable Long elementId) {
        List<Note> grades = Noterepository.findByElementId(elementId);

        if (grades.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No grades found for the specified element.");
        }

        boolean allValidated = grades.stream().allMatch(Note::isValide);
        boolean anyAbsent = grades.stream().anyMatch(Note::isAbsent);

        String status;
        if (allValidated) {
            status = "Validated";
        } else if (anyAbsent) {
            status = "Pending due to absences";
        } else {
            status = "Pending validation";
        }

        return ResponseEntity.ok("Status: " + status);
    }

    @PostMapping("/{elementId}/validate")
    public ResponseEntity<?> validateElementGrades(@PathVariable Long elementId) {
        List<Note> grades = Noterepository.findByElementId(elementId);

        if (grades.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No grades found for the specified element.");
        }

        boolean allGradesValid = grades.stream().allMatch(note -> note.getNote() >= 0 && note.getNote() <= 20);
        if (!allGradesValid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("One or more grades are invalid (must be between 0 and 20).");
        }

        grades.forEach(note -> note.setValide(true));
        Noterepository.saveAll(grades);

        return ResponseEntity.ok("All grades for the specified element have been validated.");
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

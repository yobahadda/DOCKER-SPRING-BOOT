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
    @GetMapping("/detailed/{professorId}")
    public ResponseEntity<?> fetchDetailedStatistics(@PathVariable Long professorId) {
        List<Module> modules = moduleRepository.findAll();

        // Filter modules linked to the professor via affectations
        List<Map<String, Object>> detailedStats = modules.stream()
                .filter(module -> module.getElementsDeModule().stream()
                        .anyMatch(element -> element.getAffectationsProfesseurs().stream()
                                .anyMatch(aff -> aff.getProfesseur().getId().equals(professorId))))
                .map(module -> {
                    Map<String, Object> moduleStats = new HashMap<>();
                    moduleStats.put("Module", module.getNom());

                    // Fetch notes for elements in the module
                    List<Note> notes = module.getElementsDeModule().stream()
                            .flatMap(element -> noteRepository.findByElementId(element.getId()).stream())
                            .collect(Collectors.toList());

                    double average = notes.stream().mapToInt(Note::getNote).average().orElse(0);
                    int max = notes.stream().mapToInt(Note::getNote).max().orElse(0);
                    int min = notes.stream().mapToInt(Note::getNote).min().orElse(0);

                    moduleStats.put("Average", average);
                    moduleStats.put("Max", max);
                    moduleStats.put("Min", min);
                    moduleStats.put("Total Students", notes.size());

                    return moduleStats;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(detailedStats);
    }

    // Dashboard Statistics for Professor
    @GetMapping("/dashboard/{professorId}")
    public ResponseEntity<?> fetchDashboardStats(@PathVariable Long professorId) {
        List<Module> modules = moduleRepository.findAll();

        long totalModules = modules.stream()
                .filter(module -> module.getElementsDeModule().stream()
                        .anyMatch(element -> element.getAffectationsProfesseurs().stream()
                                .anyMatch(aff -> aff.getProfesseur().getId().equals(professorId))))
                .count();

        long totalStudents = modules.stream()
                .flatMap(module -> module.getElementsDeModule().stream())
                .flatMap(element -> noteRepository.findByElementId(element.getId()).stream())
                .map(Note::getEtudiant)
                .distinct()
                .count();

        Map<String, Object> dashboardStats = new HashMap<>();
        dashboardStats.put("Total Modules", totalModules);
        dashboardStats.put("Total Students", totalStudents);

        return ResponseEntity.ok(dashboardStats);
    }

    // Statistics for Professor (Overall Performance)
    @GetMapping("/professor/{professorId}")
    public ResponseEntity<?> fetchProfessorStatistics(@PathVariable Long professorId) {
        List<Note> notes = noteRepository.findAll().stream()
                .filter(note -> note.getElementDeModule().getAffectationsProfesseurs().stream()
                        .anyMatch(aff -> aff.getProfesseur().getId().equals(professorId)))
                .collect(Collectors.toList());

        double average = notes.stream().mapToInt(Note::getNote).average().orElse(0);
        int max = notes.stream().mapToInt(Note::getNote).max().orElse(0);
        int min = notes.stream().mapToInt(Note::getNote).min().orElse(0);

        Map<String, Object> stats = new HashMap<>();
        stats.put("Average Grade", average);
        stats.put("Highest Grade", max);
        stats.put("Lowest Grade", min);
        stats.put("Total Students Evaluated", notes.size());

        return ResponseEntity.ok(stats);
    }
}

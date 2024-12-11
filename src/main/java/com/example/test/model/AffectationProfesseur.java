package com.example.test.model;
import jakarta.persistence.*;
import java.util.List;
@Entity
public class AffectationProfesseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professeur_id")
    private Professeur professeur;

    @ManyToOne
    @JoinColumn(name = "element_id")
    private ElementDeModule elementDeModule;

    // Getters and setters
}
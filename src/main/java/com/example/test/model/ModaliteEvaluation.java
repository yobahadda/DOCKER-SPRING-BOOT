package com.example.test.model;
import jakarta.persistence.*;
import java.util.List;
@Entity
public class ModaliteEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private int coefficient;

    @ManyToOne
    @JoinColumn(name = "element_id")
    private ElementDeModule elementDeModule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public ElementDeModule getElementDeModule() {
        return elementDeModule;
    }

    public void setElementDeModule(ElementDeModule elementDeModule) {
        this.elementDeModule = elementDeModule;
    }

    // Getters and setters
}
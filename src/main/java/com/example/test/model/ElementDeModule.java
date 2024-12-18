package com.example.test.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class ElementDeModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    private double coefficient;

    @OneToMany(mappedBy = "elementDeModule", cascade = CascadeType.ALL)
    private List<ModaliteEvaluation> modalitesEvaluation;

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

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public List<ModaliteEvaluation> getModalitesEvaluation() {
        return modalitesEvaluation;
    }

    public void setModalitesEvaluation(List<ModaliteEvaluation> modalitesEvaluation) {
        this.modalitesEvaluation = modalitesEvaluation;
    }
}

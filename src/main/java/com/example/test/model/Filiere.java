package com.example.test.model;

import jakarta.persistence.*;

import java.lang.Module;
import java.util.List;

@Entity
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;

    @OneToMany(mappedBy = "filiere", cascade = CascadeType.ALL)
    private List<java.lang.Module> modules;

    @OneToMany(mappedBy = "filiere", cascade = CascadeType.ALL)
    private List<Etudiant> etudiants;

    public Long getId() {
        return id;
    }

    public List<Etudiant> getEtudiants() { return etudiants;}

    public void setEtudiants(List<Etudiant> etudiants) { this.etudiants = etudiants; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<java.lang.Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
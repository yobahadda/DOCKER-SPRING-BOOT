package com.example.test.dto;

import com.example.test.model.Module;

public class ModuleDTO {
    private Long id;
    private String nom;
    private Long filiere_id;
    private int semestre;

    public ModuleDTO(Module module){
        this.id = module.getId();
        this.nom = module.getNom();
        this.filiere_id = module.getFiliere().getId();
        this.semestre = module.getSemestre();
    }

    public int getSemestre() {
        return semestre;
    }

    public Long getFiliere_id() {
        return filiere_id;
    }

    public String getNom() {
        return nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setFiliere_id(Long filiere_id) {
        this.filiere_id = filiere_id;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
}

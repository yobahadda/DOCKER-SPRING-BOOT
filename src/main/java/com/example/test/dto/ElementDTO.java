package com.example.test.dto;

import com.example.test.model.ElementDeModule;

public class ElementDTO {
    private Long id;
    private String nom;
    private Long module_id;
    private String filiere_nom;

    public ElementDTO(ElementDeModule elementDeModule){
        this.id = elementDeModule.getId();
        this.nom = elementDeModule.getNom();
        this.module_id = elementDeModule.getModule().getId();
        this.filiere_nom = elementDeModule.getModule().getFiliere().getNom();
    }

    public void setFiliere_nom(String filiere_nom) {
        this.filiere_nom = filiere_nom;
    }

    public String getFiliere_nom() {
        return filiere_nom;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Long getModule_id() {
        return module_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setModule_id(Long module_id) {
        this.module_id = module_id;
    }
}

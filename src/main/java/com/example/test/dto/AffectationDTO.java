package com.example.test.dto;

import com.example.test.model.AffectationProfesseur;

public class AffectationDTO {
    private Long id;
    private Long professeur_id;
    private Long elementDeModule_id;
    private boolean valide;

    public AffectationDTO(AffectationProfesseur affectationProfesseur){
        this.id = affectationProfesseur.getId();
        this.elementDeModule_id = affectationProfesseur.getElementDeModule().getId();
        this.professeur_id = affectationProfesseur.getProfesseur().getId();
        this.valide = affectationProfesseur.isValide();
    }

    public Long getId() {
        return id;
    }

    public Long getProfesseur_id() {
        return professeur_id;
    }

    public Long getElementDeModule_id() {
        return elementDeModule_id;
    }

    public boolean isValide() {
        return valide;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProfesseur_id(Long professeur_id) {
        this.professeur_id = professeur_id;
    }

    public void setElementDeModule_id(Long elementDeModule_id) {
        this.elementDeModule_id = elementDeModule_id;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }
}

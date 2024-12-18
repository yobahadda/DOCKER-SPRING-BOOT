package com.example.test.dto;

import com.example.test.model.Professeur;

public class ProfesseurDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String specialite;
    private String codeIdentification;
    private String login;
    private String motDePasse;

    public ProfesseurDTO(Professeur professeur) {
        this.id = professeur.getId();
        this.nom = professeur.getNom();
        this.prenom = professeur.getPrenom();
        this.specialite = professeur.getSpecialite();
        this.codeIdentification = professeur.getCodeIdentification();
        this.login = professeur.getLogin();
        this.motDePasse = professeur.getMotDePasse();
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getCodeIdentification() {
        return codeIdentification;
    }

    public String getLogin() {
        return login;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setCodeIdentification(String codeIdentification) {
        this.codeIdentification = codeIdentification;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

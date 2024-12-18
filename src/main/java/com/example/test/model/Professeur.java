package com.example.test.model;
import jakarta.persistence.*;

@Entity
public class Professeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String specialite;

    @Column(unique = true)
    private String codeIdentification;

    @Column(unique = true)
    private String login;
    private String motDePasse;

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getCodeIdentification() {
        return codeIdentification;
    }

    public void setCodeIdentification(String codeIdentification) {
        this.codeIdentification = codeIdentification;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public Professeur(){}
    public Professeur(String nom, String prenom, String specialite, String codeIdentification, String login, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.codeIdentification = codeIdentification;
        this.login = login;
        this.motDePasse = motDePasse;
    }

}
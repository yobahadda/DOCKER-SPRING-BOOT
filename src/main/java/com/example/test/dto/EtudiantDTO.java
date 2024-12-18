package com.example.test.dto;

import com.example.test.model.Etudiant;
import com.example.test.model.Note;

public class EtudiantDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String imageUrl;
    private String filiere_nom;

    public EtudiantDTO(Etudiant etudiant) {
        this.id = etudiant.getId();
        this.nom = etudiant.getNom();
        this.prenom = etudiant.getPrenom();
        this.imageUrl = etudiant.getImageUrl();
        this.filiere_nom = etudiant.getFiliere().getNom();
    }

    public EtudiantDTO(Note note){
        this.id = note.getEtudiant().getId();
        this.nom = note.getEtudiant().getNom();
        this.prenom = note.getEtudiant().getPrenom();
        this.filiere_nom = note.getEtudiant().getFiliere().getNom();
        this.imageUrl = note.getEtudiant().getImageUrl();
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

    public String getImageUrl() {
        return imageUrl;
    }

    public String getFiliere_nom() {
        return filiere_nom;
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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setFiliere(String filiere_nom) {
        this.filiere_nom = filiere_nom;
    }
}

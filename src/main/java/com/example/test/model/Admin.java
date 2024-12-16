package com.example.test.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    //add image url
    private String imageUrl;


    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<AffectationProfesseur> affectations;

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AffectationProfesseur> getAffectations() {
        return affectations;
    }

    public void setAffectations(List<AffectationProfesseur> affectations) {
        this.affectations = affectations;
    }
}

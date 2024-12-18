package com.example.test.model;
import jakarta.persistence.*;

@Entity
public class AffectationProfesseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professeur_id")
    private Professeur professeur;

    @ManyToOne
    @JoinColumn(name = "element_id")
    private ElementDeModule elementDeModule;

    private boolean valide;


    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }

    public ElementDeModule getElementDeModule() {
        return elementDeModule;
    }

    public void setElementDeModule(ElementDeModule elementDeModule) {
        this.elementDeModule = elementDeModule;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }


}
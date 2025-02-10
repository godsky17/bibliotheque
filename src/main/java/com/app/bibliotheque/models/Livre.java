package com.app.bibliotheque.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String auteur; 
    private String genre; 
    @Column(name = "date_publication")
    private LocalDate datePublication;
    private Boolean disponibilite;
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @OneToMany(mappedBy = "livre", cascade = CascadeType.ALL)
    private List<Emprunt> emprunts;


    public String getTitre() {
        return titre;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }
    public Boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Boolean diponibilite) {
        this.disponibilite = diponibilite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livre() {}
    
    public Livre(String titre, String auteur, String genre, LocalDate datePublication, Boolean disponibilite) {
        this.titre = titre;
        this.auteur = auteur;
        this.genre = genre;
        this.datePublication = datePublication;
        this.disponibilite = disponibilite ? disponibilite : true;
    }

    public enum Role {
        UTILISATEUR,
        BIBLIOTHECAIRE
    }
}

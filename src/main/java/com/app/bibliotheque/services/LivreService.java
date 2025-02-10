package com.app.bibliotheque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bibliotheque.models.Livre;
import com.app.bibliotheque.repositories.LivreRepository;

@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;

     public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    public Optional<Livre> getLivreById(Long id) {
        return livreRepository.findById(id);
    }

    public Livre addLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    public Livre updateLivre(Long id, Livre livreDetails) {
        Livre livre = livreRepository.findById(id).orElseThrow();
        livre.setTitre(livreDetails.getTitre());
        livre.setAuteur(livreDetails.getAuteur());
        livre.setDatePublication(livreDetails.getDatePublication());
        return livreRepository.save(livre);
    }

    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }

    public Livre emprunter(Long id, Livre livreDetails)
    {
        Livre livre = livreRepository.findById(id).orElseThrow();
        livre.setDisponibilite(livre.getDisponibilite());
        return livreRepository.save(livre);
    }

    public Livre rendre(Long id, Livre livreDetails)
    {
        Livre livre = livreRepository.findById(id).orElseThrow();
        livre.setDisponibilite(livre.getDisponibilite());
        return livreRepository.save(livre);
    }

}


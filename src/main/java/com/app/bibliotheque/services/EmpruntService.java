package com.app.bibliotheque.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bibliotheque.models.Emprunt;
import com.app.bibliotheque.models.Livre;
import com.app.bibliotheque.models.Utilisateur;
import com.app.bibliotheque.repositories.EmpruntRepository;
import com.app.bibliotheque.repositories.LivreRepository;
import com.app.bibliotheque.repositories.UtilisateurRepository;

@Service
public class EmpruntService {
    @Autowired
    private EmpruntRepository empruntRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private LivreRepository livreRepository;

    public List<Emprunt> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    public Optional<Emprunt> getEmpruntById(Long id) {
        return empruntRepository.findById(id);
    }

    public List<Emprunt> getEmpruntsByUtilisateur(Long utilisateurId) {
        return empruntRepository.findByUtilisateurId(utilisateurId);
    }

    public List<Emprunt> getEmpruntsByLivre(Long livreId) {
        return empruntRepository.findByLivreId(livreId);
    }

    public Emprunt creerEmprunt(Long utilisateurId, Long livreId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Livre livre = livreRepository.findById(livreId)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé"));

        if (!livre.getDisponibilite()) {
            throw new RuntimeException("Ce livre est déjà emprunté");
        }

        livre.setDisponibilite(false);
        livreRepository.save(livre);

        Emprunt emprunt = new Emprunt(utilisateur, livre, LocalDate.now(), LocalDate.now().plusWeeks(2), true);
        return empruntRepository.save(emprunt);
    }

    public Emprunt retournerEmprunt(Long empruntId) {
        Emprunt emprunt = empruntRepository.findById(empruntId)
                .orElseThrow(() -> new RuntimeException("Emprunt non trouvé"));

        emprunt.setStatus(false);
        emprunt.setDateRetour(LocalDate.now());

        Livre livre = emprunt.getLivre();
        livre.setDisponibilite(true);
        livreRepository.save(livre);

        return empruntRepository.save(emprunt);
    }

    public void supprimerEmprunt(Long id) {
        empruntRepository.deleteById(id);
    }

    public long countLivresEmpruntes() {
        return empruntRepository.countByStatus(true);
    }

    
    public long countRetoursEnAttente() {
        return empruntRepository.countByStatus(false);
    }
}

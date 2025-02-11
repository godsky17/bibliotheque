package com.app.bibliotheque.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.bibliotheque.models.Emprunt;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    List<Emprunt> findByUtilisateurId(Long utilisateurId);
    List<Emprunt> findByLivreId(Long livreId);
}

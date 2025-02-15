package com.app.bibliotheque.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.bibliotheque.models.Emprunt;
import com.app.bibliotheque.services.EmpruntService;

@RestController
@RequestMapping("/api/emprunts")
public class EmpruntController {
     @Autowired
    private EmpruntService empruntService;

    @GetMapping
    public List<Emprunt> getAllEmprunts() {
        return empruntService.getAllEmprunts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprunt> getEmpruntById(@PathVariable Long id) {
        Optional<Emprunt> emprunt = empruntService.getEmpruntById(id);
        return emprunt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public List<Emprunt> getEmpruntsByUtilisateur(@PathVariable Long utilisateurId) {
        return empruntService.getEmpruntsByUtilisateur(utilisateurId);
    }

    @GetMapping("/livre/{livreId}")
    public List<Emprunt> getEmpruntsByLivre(@PathVariable Long livreId) {
        return empruntService.getEmpruntsByLivre(livreId);
    }

    @PostMapping("/creer")
    public ResponseEntity<Emprunt> creerEmprunt(@RequestParam Long utilisateurId, @RequestParam Long livreId) {
        Emprunt nouvelEmprunt = empruntService.creerEmprunt(utilisateurId, livreId);
        return ResponseEntity.ok(nouvelEmprunt);
    }

    @PutMapping("/retourner/{id}")
    public ResponseEntity<Emprunt> retournerEmprunt(@PathVariable Long id) {
        Emprunt empruntRetourne = empruntService.retournerEmprunt(id);
        return ResponseEntity.ok(empruntRetourne);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEmprunt(@PathVariable Long id) {
        empruntService.supprimerEmprunt(id);
        return ResponseEntity.noContent().build();
    }
}

package com.app.bibliotheque.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.app.bibliotheque.models.Emprunt;
import com.app.bibliotheque.services.EmpruntService;

@Controller
@RequestMapping("/emprunts")
public class EmpruntController {

    @Autowired
    private EmpruntService empruntService;

    @GetMapping
    public String getAllEmprunts(Model model) {
        List<Emprunt> emprunts = empruntService.getAllEmprunts();
        model.addAttribute("emprunts", emprunts);
        return "dashboard/emprunts"; // Charge le fichier templates/dashboard/emprunts.html
    }

    // Afficher les détails d'un emprunt
    @GetMapping("/{id}")
    public String getEmpruntById(@PathVariable Long id, Model model) {
        Optional<Emprunt> emprunt = empruntService.getEmpruntById(id);
        if (emprunt.isPresent()) {
            model.addAttribute("emprunt", emprunt.get());
            return "emprunt_details"; // Vue Thymeleaf : emprunt_details.html
        }
        return "redirect:/emprunts";
    }

    // Afficher les emprunts d'un utilisateur
    @GetMapping("/utilisateur/{utilisateurId}")
    public String getEmpruntsByUtilisateur(@PathVariable Long utilisateurId, Model model) {
        List<Emprunt> emprunts = empruntService.getEmpruntsByUtilisateur(utilisateurId);
        model.addAttribute("emprunts", emprunts);
        return "emprunts"; // Vue Thymeleaf réutilisée
    }

    // Afficher les emprunts d'un livre
    @GetMapping("/livre/{livreId}")
    public String getEmpruntsByLivre(@PathVariable Long livreId, Model model) {
        List<Emprunt> emprunts = empruntService.getEmpruntsByLivre(livreId);
        model.addAttribute("emprunts", emprunts);
        return "emprunts";
    }

    // Afficher le formulaire de création d'un emprunt
    @GetMapping("/ajouter")
    public String showCreateForm(Model model) {
        model.addAttribute("emprunt", new Emprunt());
        return "emprunt_form"; // Vue Thymeleaf : emprunt_form.html
    }

    // Créer un emprunt
    @PostMapping("/ajouter")
    public String creerEmprunt(@ModelAttribute Emprunt emprunt) {
        empruntService.creerEmprunt(emprunt.getUtilisateur().getId(), emprunt.getLivre().getId());
        return "redirect:/emprunts";
    }

    // Retourner un emprunt
    @GetMapping("/retourner/{id}")
    public String retournerEmprunt(@PathVariable Long id) {
        empruntService.retournerEmprunt(id);
        return "redirect:/emprunts";
    }

    // Supprimer un emprunt
    @GetMapping("/supprimer/{id}")
    public String supprimerEmprunt(@PathVariable Long id) {
        empruntService.supprimerEmprunt(id);
        return "redirect:/emprunts";
    }
}
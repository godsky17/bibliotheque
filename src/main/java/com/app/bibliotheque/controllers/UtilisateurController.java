package com.app.bibliotheque.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.app.bibliotheque.models.Utilisateur;
import com.app.bibliotheque.services.UtilisateurService;

@Controller
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // Afficher la liste des utilisateurs
    @GetMapping
    public String getAllUtilisateurs(Model model) {
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
        model.addAttribute("utilisateurs", utilisateurs);
        return "dashboard/list-utilisateurs"; // Affiche la vue utilisateurs.html
    }

    // Afficher les détails d'un utilisateur
    @GetMapping("/{id}")
    public String getUtilisateurById(@PathVariable Long id, Model model) {
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(id);
        if (utilisateur.isPresent()) {
            model.addAttribute("utilisateur", utilisateur.get());
            return "utilisateur_details"; // Affiche la vue utilisateur_details.html
        }
        return "redirect:/utilisateurs"; // Redirection si l'utilisateur n'existe pas
    }

    // Afficher le formulaire de création d'un utilisateur
    @GetMapping("/ajouter")
    public String showCreateForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "dashboard/ajouter-utilisateur"; // Affiche la vue utilisateur_form.html
    }

    // Enregistrer un nouvel utilisateur
    @PostMapping("/ajouter")
    public String createUtilisateur(@ModelAttribute Utilisateur utilisateur) {
        utilisateurService.createUtilisateur(utilisateur);
        return "redirect:/utilisateurs";
    }

    // Afficher le formulaire de modification d'un utilisateur
    @GetMapping("/modifier/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(id);
        if (utilisateur.isPresent()) {
            model.addAttribute("utilisateur", utilisateur.get());
            return "dashboard/update-utilisateurs"; // Affiche le formulaire de modification
        }
        return "redirect:/utilisateurs";
    }

    // Mettre à jour un utilisateur
    @PostMapping("/modifier/{id}")
    public String updateUtilisateur(@PathVariable Long id, @ModelAttribute Utilisateur utilisateur) {
        utilisateurService.updateUtilisateur(id, utilisateur);
        return "redirect:/utilisateurs";
    }

    // Supprimer un utilisateur
    @GetMapping("/supprimer/{id}")
    public String deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return "redirect:/utilisateurs";
    }
}

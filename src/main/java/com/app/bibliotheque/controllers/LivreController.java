package com.app.bibliotheque.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.bibliotheque.models.Livre;
import com.app.bibliotheque.services.LivreService;




@Controller
@RequestMapping("/livres")
public class LivreController {

   @Autowired
    private LivreService livreService;

    // Affiche tous les livres
    @GetMapping
    public String getAllLivres(Model model) {
        List<Livre> livres = livreService.getAllLivres();
        model.addAttribute("livres", livres);
        return "dashboard/tables";  // Retourne la vue 'livres.html'
    }

    // Affiche un livre spécifique par son ID
    @GetMapping("/{id}")
    public String getLivreById(@PathVariable Long id, Model model) {
        Optional<Livre> livre = livreService.getLivreById(id);
        if (livre.isPresent()) {
            model.addAttribute("livre", livre.get());
            return "dashboard/update-livre";  // Retourne la vue 'livre-detail.html'
        } else {
            // Livre non trouvé, rediriger vers la liste des livres
            return "redirect:dashboard/tables";
        }
    }

    // Affiche le formulaire d'ajout d'un livre
    @GetMapping("/ajouter")
    public String showAddLivreForm(Model model) {
        model.addAttribute("livre", new Livre());
        return "dashboard/ajouter-livre";  // Retourne la vue 'ajouter-livre.html'
    }

    // Ajoute un nouveau livre
    @PostMapping("/ajouter")
    public String addLivre(@ModelAttribute Livre livre) {
        livreService.addLivre(livre);
        return "redirect:/livres";  // Redirige vers la liste des livres après ajout
    }

    // Met à jour les informations d'un livre
    @PostMapping("/modifier/{id}")
    public String updateLivre(@PathVariable Long id, @ModelAttribute Livre livre) {
        livreService.updateLivre(id, livre);
        return "redirect:/livres";  // Redirige vers la liste des livres après modification
    }

    // Supprime un livre
    @PostMapping("/supprimer/{id}")
    public String deleteLivre(@PathVariable Long id) {
        livreService.deleteLivre(id);
        return "redirect:/livres";  // Redirige vers la liste des livres après suppression
    }

    // // Gère l'emprunt d'un livre
    // @PostMapping("/emprunter/{id}")
    // public String emprunterLivre(@PathVariable Long id) {
    //     livreService.emprunter(id);
    //     return "redirect:/livres";  // Redirige vers la liste des livres après emprunt
    // }

    // // Gère le retour d'un livre
    // @PostMapping("/rendre/{id}")
    // public String rendreLivre(@PathVariable Long id) {
    //     livreService.rendre(id);
    //     return "redirect:/livres";  // Redirige vers la liste des livres après retour
    // }
}

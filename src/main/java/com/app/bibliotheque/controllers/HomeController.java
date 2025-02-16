package com.app.bibliotheque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.app.bibliotheque.services.EmpruntService;
import com.app.bibliotheque.services.LivreService;
import com.app.bibliotheque.services.UtilisateurService;


@Controller
public class HomeController {

    private LivreService livreService;
    private EmpruntService empruntService;
    private UtilisateurService utilisateurService;

    @GetMapping("/")
    public String home(Model model){
        long totalLivres = livreService.countLivres();
        
        long totalUtilisateurs = utilisateurService.countUtilisateurs();
        
        long livresEmpruntes = empruntService.countLivresEmpruntes();
        
        long retoursEnAttente = empruntService.countRetoursEnAttente();
        
        // Ajouter les données au modèle
        model.addAttribute("totalLivres", totalLivres);
        model.addAttribute("totalUtilisateurs", totalUtilisateurs);
        model.addAttribute("livresEmpruntes", livresEmpruntes);
        model.addAttribute("retoursEnAttente", retoursEnAttente);
        return "home";
    }

    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

    @GetMapping("/bibliotheque")
    public String showBibliotheque(){
        return "bibliotheque";
    }

    @GetMapping("/dashboard")
    public String showDashboard(){
        return "dashboard/index";
    }
}

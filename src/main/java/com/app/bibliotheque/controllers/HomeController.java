package com.app.bibliotheque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
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

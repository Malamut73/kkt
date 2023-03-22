package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.catalog.LKSite;
import com.tehnology.kkt.services.LKSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LKSiteController {

    private final LKSiteService lkSiteService;

    @GetMapping("/lkSites")
    public String lkSites(Model model){
        model.addAttribute("lkSites", lkSiteService.findAll());
        return "lksites/lk-sites";
    }

    @GetMapping("/lkSites/new")
    public String createLKSite(Model model){
        return "lksites/lk-site";
    }

    @PostMapping("/lkSites/new")
    public String createLKSite(@ModelAttribute LKSite lkSite){
        lkSiteService.save(lkSite);
        return "redirect:/lkSites";
    }

    @GetMapping("/lkSites/{lkSiteid}/delete")
    public String deleteLKSite(@PathVariable("lkSiteid") LKSite lkSite){
        lkSiteService.delete(lkSite);
        return "redirect:/lkSites";
    }

//    @PostMapping("/lkSites/{lksiteid}")
//    public String lkSiteEdit(@ModelAttribute LKSite lkSite){
//        lk
//    }
}

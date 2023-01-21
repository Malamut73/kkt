package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.catalog.Organization;
import com.tehnology.kkt.services.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;


    @GetMapping("/organization")
    public String listOrganization(Model model){
        model.addAttribute("organizations", organizationService.findAll());
        return "organizations";
    }

    @GetMapping("/organization/create")
    public String createOrganization(Model model){
        System.out.println(1);
        model.addAttribute("organization", new Organization());
        return "create-organization";
    }

    @PostMapping("/organization/create")
    public String createOrganization(Organization organization, Model model){
        System.out.println(2);
        organizationService.save(organization);
        model.addAttribute("organization", organizationService.findAll());
        return "redirect:/organization";
    }

    @GetMapping("/organization/create/clients")
    public String createOrganizationFromClientCreate(Model model){
        model.addAttribute("clients", "clients");
        model.addAttribute("organization", new Organization());
        return "create-organization";
    }

    @PostMapping("/organization/create/clients")
    public String returnToClientCreate(Organization organization, Model model){
        organizationService.save(organization);
        model.addAttribute("user", new User());
        model.addAttribute("organizations", organizationService.findAll());
        return "redirect:/clients/create";
    }

}

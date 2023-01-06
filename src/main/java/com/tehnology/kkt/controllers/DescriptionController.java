package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Description;
import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.User;
import com.tehnology.kkt.services.DescriptionService;
import com.tehnology.kkt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DescriptionController {

    private final DescriptionService descriptionService;
    private final UserService userService;


    @GetMapping("/descriptions")
    public String descriptionsList(Model model) {
        model.addAttribute("descriptions", descriptionService.findAll());
        return "descriptions";
    }

    @GetMapping("/descriptions/create")
    public String createDescription(Model model) {
        model.addAttribute("description", new Description());
        return "create-description";
    }

    @PostMapping("/descriptions/create")
    public String createDescription(Description description, Model model) {
        descriptionService.saveDescription(description);
        return "redirect:/descriptions";
    }

    @GetMapping("/descriptions/create/clients/{clientid}")
    public String createDescriptionFromProduct(@PathVariable Long clientid,
                                               Description description, Model model) {
        model.addAttribute("description", new Description());
        model.addAttribute("clientid", clientid);
        return "create-description";
    }

    @PostMapping("/descriptions/create/clients/{clientid}")
    public String returnToProductCreate(@PathVariable Long clientid,
                                               Description description, Model model) {
        descriptionService.saveDescription(description);
        model.addAttribute("clientid", clientid);
        return "redirect:/clients/{clientid}/product";
    }




}

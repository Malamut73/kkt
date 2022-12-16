package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Description;
import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.services.DescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DescriptionController {

    private final DescriptionService descriptionService;

    @GetMapping("/products")
    public String descriptionsList(Description description, Model model) {
        model.addAttribute("descriptions", descriptionService.findAll());
        return "products";
    }

    @PostMapping("/products")
    public String descriptionsList(Description description) {

        descriptionService.saveDescription(description);

        return "redirect:/products";
    }

    @GetMapping("/product/create")
    public String createDescription(Product product, Model model) {
        model.addAttribute("description",new Description());
        return "create-description";
    }


}

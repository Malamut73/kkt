package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.Taxation;
import com.tehnology.kkt.services.ProductService;
import com.tehnology.kkt.services.TaxationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TaxationController {

    private final TaxationService taxationService;
    private final ProductService productService;

    @GetMapping("/taxations")
    public String taxtations(Model model){
        model.addAttribute("taxations", taxationService.findAll());
        return "taxations";
    }

    @GetMapping("/taxations/create")
    public String createTaxtation(Model model){
        model.addAttribute("taxation", new Taxation());
        return "create-taxation";
    }

    @PostMapping("/taxations/create")
    public String createTaxtation(Taxation taxation){
        taxationService.save(taxation);
        return "redirect:/taxations";
    }

    @GetMapping("/clients/{clientid}/product/{productId}/taxation")
    public String addTaxation(@PathVariable Long clientid,
                              @PathVariable Long productId,  Model model){
        model.addAttribute("taxations", taxationService.findAll());
        model.addAttribute("taxation", new Taxation());
        model.addAttribute("clientid", clientid);
        model.addAttribute("productId", productId);
        return "choose-taxation";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/taxation")
    public String addTaxation(@PathVariable Long productId, Taxation taxation){
        Product product = productService.findById(productId);
        product.setTaxation(taxationService.findById(taxation.getId()));
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productId}";

    }

    @GetMapping("/clients/{clientid}/product/{productId}/taxation/{taxationid}/edit")
    public String editTaxation(@PathVariable Long taxationid,
                                     @PathVariable Long clientid,
                                     @PathVariable Long productId, Model model){
        model.addAttribute("taxation", taxationService.findById(taxationid));
        model.addAttribute("taxationid", taxationid);
        model.addAttribute("clientid", clientid);
        model.addAttribute("productId", productId);
        model.addAttribute("taxations", taxationService.findAll());
        return "choose-taxation";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/taxation/{taxationid}/edit")
    public String editTaxation(@PathVariable Long productId, Taxation taxation){
        Product product = productService.findById(productId);
        product.setTaxation(taxationService.findById(taxation.getId()));
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productId}";

    }
    @PostMapping("/clients/{clientid}/product/{productId}/taxation/{taxationid}/delete")
    public String deleteTaxation(@PathVariable Long productId){
        Product product = productService.findById(productId);
        product.setTaxation(null);
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productId}";

    }

}

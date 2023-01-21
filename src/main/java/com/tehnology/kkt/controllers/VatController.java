package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.enums.VAT;
import com.tehnology.kkt.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class VatController {

    private final ProductService productService;

    @PostMapping("/clients/{clientid}/product/{productId}/addvat")
    public String addVat(@PathVariable Long productId){
        Product product = productService.findById(productId);
        product.setVat(VAT.Да);
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productId}";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/deletevat")
    public String deleteVat(@PathVariable Long productId){
        Product product = productService.findById(productId);
        product.setVat(null);
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productId}";
    }
}

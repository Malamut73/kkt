package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.enums.Egais;
import com.tehnology.kkt.models.enums.Excise;
import com.tehnology.kkt.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class EgaisController {

    private final ProductService productService;

    @PostMapping("/clients/{clientid}/product/{productId}/addegais")
    public String addVat(@PathVariable Long productId){
        Product product = productService.findById(productId);
        product.setEgais(Egais.Да);
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productId}";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/deleteegais")
    public String deleteVat(@PathVariable Long productId){
        Product product = productService.findById(productId);
        product.setEgais(null);
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productId}";
    }

}

package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.enums.Excise;
import com.tehnology.kkt.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ExciseController {

    private final ProductService productService;

    @PostMapping("/clients/{clientid}/product/{productId}/addexcise")
    public String addVat(@PathVariable Long productId, Principal principal){
        Product product = productService.findById(productId);
        product.setExcise(Excise.Да);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("добавил акцизы").build());
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productId}";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/deleteexcise")
    public String deleteVat(@PathVariable Long productId, Principal principal){
        Product product = productService.findById(productId);
        product.setExcise(null);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("удалил акцизы").build());
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productId}";
    }

}

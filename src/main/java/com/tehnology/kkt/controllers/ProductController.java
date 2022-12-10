package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.services.ProductService;
import com.tehnology.kkt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/clients/{id}/product")
    public String crateProduct(@PathVariable("id") Long id, Product product, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("product", new Product());
        return "create-product";
    }

    @PostMapping("/clients/{id}/product")
    public String crateProduct(@PathVariable("id") Long id,Product product) {

        product.setUser(userService.findById(id));
        product.setId(null);
        productService.saveProduct(product);

        return "redirect:/clients/{id}";
    }
}

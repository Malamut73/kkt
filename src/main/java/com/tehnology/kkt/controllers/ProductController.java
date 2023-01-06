package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Description;
import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.extraclasses.LK;
import com.tehnology.kkt.models.extraclasses.OFD;
import com.tehnology.kkt.models.extraclasses.firdirectory.Tariff;
import com.tehnology.kkt.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final DescriptionService descriptionService;
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/clients/{clientid}/product")
    public String crateProduct(@PathVariable Long clientid, Product product, Model model) {
        model.addAttribute("id", clientid);
        model.addAttribute("product", new Product());
        model.addAttribute("descriptions", descriptionService.findAll());
        return "create-product";
    }

    @PostMapping("/clients/{clientid}/product")
    public String crateProduct(@PathVariable Long clientid, Product product) {
        product.setUser(userService.findById(clientid));
        product.setId(null);
        product.setDescription(descriptionService.findById(product.getDescription().getId()));
        productService.saveProduct(product);

        return "redirect:/clients/{clientid}";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/delete")
    public String deleteProduct(@PathVariable Long clientid, @PathVariable Long productid){
            productService.deleteProduct(productService.findById(productid));
            return "redirect:/clients/{clientid}";
    }

    @GetMapping("/clients/{clientid}/product/{productid}")
    public String productInfo(@PathVariable Long productid, Model model){
            model.addAttribute("product", productService.findById(productid));
            return "product-info";
    }

    @GetMapping("/clients/{clientid}/product/{productid}/edit")
    public String editProduct(@PathVariable Long clientid,
                              @PathVariable Long productid, Model model){
        model.addAttribute("product", productService.findById(productid));
        model.addAttribute("descriptions", descriptionService.findAll());
        model.addAttribute("clientid", clientid);
        model.addAttribute("productid", productid);
        return "edit-product";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/edit")
    public String editProduct(@PathVariable Long clientid,
                              @PathVariable Long productid, Product product, Model model){

        product.setDescription(descriptionService.findById(product.getDescription().getId()));
        product.setId(productid);
        product.setUser(userService.findById(clientid));
        productService.saveProduct(product);
        model.addAttribute("product", productService.findById(productid));

        return "redirect:/clients/{clientid}/product/{productid}";
    }

    @GetMapping("/clients/{clientid}/product/{productid}/changeclient")
    public String changeClient(@PathVariable Long productid, Model model){
        model.addAttribute("clients", userService.findAllClients());
        model.addAttribute("product", productService.findById(productid));
        return "change-client";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/changeclient")
    public String changeClient(@PathVariable Long productid, Product productFrom){
        Product product = productService.findById(productid);
        product.setUser(userService.findById(productFrom.getUser().getId()));
        productService.saveProduct(product);
        return "redirect:/clients/" + productFrom.getUser().getId() + "/product/{productid}/";

    }





}

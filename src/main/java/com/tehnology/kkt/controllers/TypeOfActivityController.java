package com.tehnology.kkt.controllers;


import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.catalog.TypeOfActivity;
import com.tehnology.kkt.services.ProductService;
import com.tehnology.kkt.services.TypeOfActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class TypeOfActivityController {

    private final TypeOfActivityService typeOfActivityService;
    private final ProductService productService;


    @GetMapping("/typeofactivity")
    public String typeOfActivities(Model model){
        model.addAttribute("typeofactivities", typeOfActivityService.findAll());
        return "typeofactivities";
    }

    @GetMapping("/typeofactivity/create")
    public String createTypeOfActivity(Model model){
        model.addAttribute("typeofactivity", new TypeOfActivity());
        return "create-typeofactivities";
    }

    @PostMapping("/typeofactivity/create")
    public String createTypeOfActivity(TypeOfActivity typeOfActivity) {
        typeOfActivityService.save(typeOfActivity);
        return "redirect:/typeofactivity";
    }

    @GetMapping("/clients/{clientid}/product/{productId}/typeofactivity")
    public String addTypeOfActivity(@PathVariable Long clientid,
                                    @PathVariable Long productId,  Model model){
        model.addAttribute("typeofactivities", typeOfActivityService.findAll());
        model.addAttribute("clientid", clientid);
        model.addAttribute("productId", productId);
        return "choose-typeofactivity";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/typeofactivity")
    public String addTypeOfActivity(@PathVariable Long productId, Principal principal,
                                    @RequestParam("typeofactivity") String typeofactivity){
        Product product = productService.findById(productId);
        product.setTypeOfActivity(typeofactivity);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("изменил вид деятельности").build());
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productId}";

    }

    @GetMapping("/clients/{clientid}/product/{productId}/typeOfActivity/{typeofactivityid}/edit")
    public String editTypeOfActivity(@PathVariable Long typeofactivityid,
                                     @PathVariable Long clientid,
                                     @PathVariable Long productId, Model model){
        model.addAttribute("typeOfActivity", typeOfActivityService.findById(typeofactivityid));
        model.addAttribute("typeofactivityid", typeofactivityid);
        model.addAttribute("clientid", clientid);
        model.addAttribute("productId", productId);
        model.addAttribute("typeofactivities", typeOfActivityService.findAll());
        return "choose-typeofactivity";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/typeOfActivity/{typeofactivityid}/edit")
    public String editTypeOfActivity(@PathVariable Long productId, TypeOfActivity typeOfActivity){
        Product product = productService.findById(productId);
        product.setTypeOfActivity(typeOfActivity.getName());
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productId}";

    }

    @PostMapping("/clients/{clientid}/product/{productId}/typeofactivity/delete")
    public String deleteTypeOfActivity(@PathVariable Long productId, Principal principal){
        Product product = productService.findById(productId);
        product.setTypeOfActivity(null);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("удалил вид деятельности").build());
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productId}";

    }
}

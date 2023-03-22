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
    public String addTypeOfActivity(@PathVariable Long clientid, @PathVariable("productId") Product product,
                                    @PathVariable Long productId,  Model model){
        model.addAttribute("typeofactivities", typeOfActivityService.findAll());
        model.addAttribute("product", product);
        model.addAttribute("clientid", clientid);
        model.addAttribute("productId", productId);
        return "typeofactivity/choose-typeofactivity";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/typeofactivity")
    public String addTypeOfActivity(@RequestParam(name = "typeof[]") String[] typeof,
                                    @PathVariable("productId") Product product, Principal principal){
        productService.setTypeOfActivity(product, typeof, principal);
        return "redirect:/clients/{clientid}/product/{productId}";
    }

    @GetMapping("/typeofactivity/{typeofactivitiyid}/delete")
    public String deleteTypeOfActivity(@PathVariable("typeofactivitiyid") TypeOfActivity typeOfActivity){
        typeOfActivityService.delete(typeOfActivity);
        return "redirect:/typeofactivity";
    }

}

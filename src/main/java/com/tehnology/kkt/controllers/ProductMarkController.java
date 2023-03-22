package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.catalog.ProductMark;
import com.tehnology.kkt.services.ProductMarkService;
import com.tehnology.kkt.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductMarkController {

    private final ProductMarkService productMarkService;
    private final ProductService productService;

    @GetMapping("/marks")
    public String marks(Model model){
        model.addAttribute("marks", productMarkService.findAll());
        return "mark/marks";
    }

    @GetMapping("/marks/create")
    public String createMarks(){
        return "mark/create-mark";
    }

    @PostMapping("marks/create")
    public String createMark(@ModelAttribute ProductMark productMark){
        productMarkService.save(productMark);
        return "redirect:/marks";
    }

    @GetMapping("/marks/{markid}/delete")
    public String deleteMark(@PathVariable("markid")ProductMark productMark){
        productMarkService.delete(productMark);
        return "redirect:/marks";
    }

    @GetMapping("/clients/{clientid}/product/{productid}/productMark")
    public String addMarkToProduct(Model model, @PathVariable("productid") Product product,
                                   @PathVariable("clientid") Long clientid){
        model.addAttribute("product", product);
        model.addAttribute("clientid", clientid);
        model.addAttribute("marks", productMarkService.findAll());
        return "mark/choose-mark";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/productMark")
    public String addMarkToProduct(@RequestParam("marks[]") String[] marks, Principal principal,
                                   @PathVariable("productid") Product product){
        productService.setProductMark(marks, principal, product);
        return "redirect:/clients/{clientid}/product/{productid}/";
    }
}

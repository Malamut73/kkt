package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.LK;
import com.tehnology.kkt.models.catalog.LKSite;
import com.tehnology.kkt.services.LKService;
import com.tehnology.kkt.services.LKSiteService;
import com.tehnology.kkt.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class LKController {

    private final ProductService productService;
    private final LKService lkService;
    private final LKSiteService lkSiteService;



    @GetMapping("/clients/{clientid}/product/{productid}/lk")
    public String addLK(@PathVariable Long clientid, @PathVariable Long productid, Model model){
//        model.addAttribute("lk", new LK());
        model.addAttribute("clientid", clientid);
        model.addAttribute("productid", productid);
        model.addAttribute("lksites", lkSiteService.findAll());
        return "lksites/create-lk";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/lk")
    public String addLK(@PathVariable Long clientid, Principal principal,
                        @PathVariable Long productid, Model model,
                        @ModelAttribute LK lk){
        Product product = productService.findById(productid);
        product.setLk(lk);
        product.getComments().add(Comment.builder()
                .user(principal.getName()).text("добавил данные в личный кабинет").build());
        productService.saveProduct(product);

        return "redirect:/clients/{clientid}/product/{productid}";
    }


    @PostMapping("/clients/{clientid}/product/{productid}/lk/{lkid}/delete")
    public String deleteLK(@PathVariable Long clientid, Principal principal,
                           @PathVariable Long productid,
                           @PathVariable Long lkid, Model model){
        Product product = productService.findById(productid);
        LK lk = product.getLk();
        product.setLk(null);
        product.getComments().add(Comment.builder()
                .user(principal.getName()).text("удалил данные о личном кабинете").build());
        productService.saveProduct(product);
        lkService.deleteLk(lk);

        return "redirect:/clients/{clientid}/product/{productid}";
    }

    @GetMapping("/clients/{clientid}/product/{productid}/lk/{lkid}/edit")
    public String editLK(@PathVariable Long clientid,
                           @PathVariable Long productid,
                           @PathVariable Long lkid,Model model){
        model.addAttribute("lk", lkService.findById(lkid));
        model.addAttribute("clientid", clientid);
        model.addAttribute("productid", productid);
        model.addAttribute("lkid", lkid);
        model.addAttribute("lksites", lkSiteService.findAll());

        return "lksites/create-lk";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/lk/{lkid}/edit")
    public String editLK(@PathVariable Long clientid, Principal principal,
                         @PathVariable Long productid,
                         @ModelAttribute LK lk, Model model){
        Product product = productService.findById(productid);
        product.setLk(lk);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("изменил данные личного кабинета").build());
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productid}";
    }

}

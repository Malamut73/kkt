package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.SimCard;
import com.tehnology.kkt.models.catalog.Internet;
import com.tehnology.kkt.services.InternetService;
import com.tehnology.kkt.services.ProductService;
import com.tehnology.kkt.services.SimCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class InternetController {

    private final InternetService internetService;
    private final ProductService productService;
    private final SimCardService simCardService;

    @GetMapping("/internets")
    public String internets(Model model){
        model.addAttribute("internets", internetService.findAll());
        return "internets";
    }

    @GetMapping("/internets/create")
    public String createInternet(Model model){
        model.addAttribute("internet", new Internet());
        return "create-internet";
    }

    @PostMapping("/internets/create")
    public String creatInternet(Internet internet){
        internetService.save(internet);
        return "redirect:/internets";
    }

    @GetMapping("/clients/{clientid}/product/{productid}/internet")
    public String addInternet(@PathVariable Long clientid,
                              @PathVariable Long productid, Model model){
        model.addAttribute("internets", internetService.findAll());
        model.addAttribute("clientid", clientid);
        model.addAttribute("productid", productid);
        return "choose-internet";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/internet")
    public String addInternet(@PathVariable("productid") Product product, Principal principal,
                              @ModelAttribute SimCard simCard ){
        product.setSimCard(simCard);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("добавил сим-карту").build());
        productService.saveProduct(product);

        return "redirect:/clients/{clientid}/product/{productid}";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/internet/{simCardid}/delete")
    public String deleteInternet(@PathVariable("productid") Product product,
                                 @PathVariable("simCardid") SimCard simCard, Principal principal){
//        product.setInternet(null);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("удалил интернет").build());
        simCardService.delete(simCard);
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productid}";
    }

    @GetMapping("/clients/{clientid}/product/{productid}/internet/edit")
    public String editInternet(@PathVariable Long productid,
                               @PathVariable Long clientid, Model model){
        model.addAttribute("internets", internetService.findAll());
        model.addAttribute("product", productService.findById(productid));
        model.addAttribute("productid", productid);
        model.addAttribute("clientid", clientid);

        return "change-internet";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/internet/edit")
    public String editInternet(@ModelAttribute SimCard simCard, Principal principal,
                               @PathVariable("productid") Product product){
        product.setSimCard(simCard);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("изменил интернет").build());
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productid}";
    }

    @GetMapping("/internets/{internetid}/delete")
    public String deleteInternet(@PathVariable("internetid") Internet internet){
        internetService.delete(internet);
        return "redirect:/internets";
    }

}

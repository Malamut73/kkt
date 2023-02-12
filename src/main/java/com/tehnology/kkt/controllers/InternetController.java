package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.catalog.Internet;
import com.tehnology.kkt.services.InternetService;
import com.tehnology.kkt.services.ProductService;
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
public class InternetController {

    private final InternetService internetService;
    private  final ProductService productService;

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
    public String addInternet(@PathVariable Long productid, Principal principal,
                              @RequestParam("name") String name ){
        Product product = productService.findById(productid);
        product.setInternet(name);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("добавил интернет").build());
        productService.saveProduct(product);

        return "redirect:/clients/{clientid}/product/{productid}";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/internet/delete")
    public String deleteInternet(@PathVariable Long productid, Principal principal){
        Product product = productService.findById(productid);
        product.setInternet(null);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("удалил интернет").build());
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productid}";
    }

    @GetMapping("/clients/{clientid}/product/{productid}/internet/edit")
    public String editInternet(@PathVariable Long productid,
                               @PathVariable Long clientid, Model model){
        model.addAttribute("internets", internetService.findAll());
        model.addAttribute("productid", productid);
        model.addAttribute("clientid", clientid);

        return "choose-internet";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/internet/edit")
    public String editInternet(@RequestParam("name") String name, Principal principal,
                               @PathVariable("productid") Product product){
        product.setInternet(name);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("изменил интернет").build());
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productid}";
    }

}

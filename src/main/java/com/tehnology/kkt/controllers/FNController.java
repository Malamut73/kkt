package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.FN;
import com.tehnology.kkt.services.FNService;
import com.tehnology.kkt.services.ProductService;
import com.tehnology.kkt.services.TariffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class FNController {

    private final ProductService productService;
    private final FNService fnService;
    private final TariffService tariffService;


    @GetMapping("/clients/{clientid}/product/{productid}/fn")
    public String fn(@PathVariable Long clientid,
                     @PathVariable Long productid, Model model){
        model.addAttribute("fn", new FN());
        model.addAttribute("tariffs", tariffService.findAll());
        model.addAttribute("clientid", clientid);
        model.addAttribute("productid", productid);
        return "create-fn";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/fn")
    public String fn(@PathVariable Long productid, Principal principal, FN fn){
        Product product = productService.findById(productid);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("создал фн").build());
        product.setFn(fn);
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productid}";
    }

    @GetMapping("/clients/{clientid}/product/{productid}/fn/{fnid}/edit")
    public String editFn(@PathVariable Long fnid,
                         @PathVariable Long productid,
                         @PathVariable Long clientid, Model model){

        model.addAttribute("tariffs", tariffService.findAll());
        model.addAttribute("fn", fnService.findById(fnid));
        model.addAttribute("productid", productid);
        model.addAttribute("clientid", clientid);

        return "create-fn";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/fn/{fnid}/edit")
    public String editFN(Principal principal,
                         @PathVariable Long clientid,
                         @PathVariable("productid") Product product, FN fn){
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("отредактировал фн").build());
        product.setFn(fn);
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productid}";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/fn/{fnid}/delete")
    public String deleteFN(@PathVariable Long fnid, Principal principal,
                           @PathVariable Long productid){
        Product product = productService.findById(productid);
        product.setFn(null);
        product.getComments().add(Comment.builder().text("удалил фн")
                .user(principal.getName()).build());
        productService.saveProduct(product);
        fnService.deleteById(fnid);
        return "redirect:/clients/{clientid}/product/{productid}";
    }

}

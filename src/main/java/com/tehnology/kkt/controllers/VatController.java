package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.catalog.Vat;
import com.tehnology.kkt.services.ProductService;
import com.tehnology.kkt.services.VatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class VatController {

    private final ProductService productService;
    private final VatService vatService;

    @GetMapping("/vats")
    public String vats(Model model){
        model.addAttribute("vats", vatService.findAll());
        return "vat/vats";
    }

    @GetMapping("/vat")
    public String vat(){
        return "vat/create-vat";
    }

    @PostMapping("/vat")
    public String vat(@ModelAttribute Vat vat){
        vatService.save(vat);
        return "redirect:/vats";
    }

    @GetMapping("/clients/{clientid}/product/{productId}/addvat")
    public String addVat(@PathVariable("productId") Long productId,
                         @PathVariable("clientid") Long clientid, Model model){
        model.addAttribute("clientid", clientid);
        model.addAttribute("productId", productId);
        model.addAttribute("vats", vatService.findAll());
        return "vat/add-vat";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/addvat")
    public String addVat(@RequestParam("vat") String vat, Principal principal,
                         @PathVariable("productId") Product product){
        product.setVat(vat);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("добавил ставку НДС").build());
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productId}";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/deletevat")
    public String deleteVat(@PathVariable Long productId, Principal principal){
        Product product = productService.findById(productId);
        product.setVat(null);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("удалил НДС").build());
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productId}";
    }

    @GetMapping("/vats/{vatid}/delete")
    public String deleteVat(@PathVariable("vatid") Vat vat){
        vatService.delete(vat);
        return "redirect:/vats";
    }
}

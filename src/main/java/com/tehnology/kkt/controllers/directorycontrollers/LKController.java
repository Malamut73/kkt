package com.tehnology.kkt.controllers.directorycontrollers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.extraclasses.LK;
import com.tehnology.kkt.services.LKService;
import com.tehnology.kkt.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LKController {

    private final ProductService productService;
    private final LKService lkService;



    @GetMapping("/clients/{clientid}/product/{productid}/lk")
    public String addLK(@PathVariable Long clientid, @PathVariable Long productid, Model model){
        model.addAttribute("lk", new LK());
        model.addAttribute("clientid", clientid);
        model.addAttribute("productid", productid);
        return "creaters/create-lk";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/lk")
    public String addLK(@PathVariable Long clientid, @PathVariable Long productid, Model model, LK lk){
        Product product = productService.findById(productid);
        product.setLk(lk);
        productService.saveProduct(product);

        return "redirect:/clients/{clientid}/product/{productid}";
    }


    @PostMapping("/clients/{clientid}/product/{productid}/lk/{lkid}/delete")
    public String deleteLK(@PathVariable Long clientid,
                           @PathVariable Long productid,
                           @PathVariable Long lkid, Model model){
        Product product = productService.findById(productid);
        LK lk = product.getLk();
        product.setLk(null);
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

        return "creaters/create-lk";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/lk/{lkid}/edit")
    public String editLK(@PathVariable Long clientid,
                         @PathVariable Long productid,
                         @PathVariable Long lkid, LK lk, Model model){
        Product product = productService.findById(productid);
        product.setLk(lk);
        productService.saveProduct(product);
        lkService.deleteLKById(lkid);

//        model.addAttribute("lk", lk);

        return "redirect:/clients/{clientid}/product/{productid}";
    }

}

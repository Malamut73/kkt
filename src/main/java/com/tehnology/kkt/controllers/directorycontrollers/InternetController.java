package com.tehnology.kkt.controllers.directorycontrollers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.extraclasses.firdirectory.Internet;
import com.tehnology.kkt.services.InternetService;
import com.tehnology.kkt.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute("internet", new Internet());
        model.addAttribute("clientid", clientid);
        model.addAttribute("productid", productid);
        model.addAttribute("product", productService.findById(productid));
        return "choose-internet";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/internet")
    public String addInternet(@PathVariable Long productid, Internet internet){
        Product product = productService.findById(productid);
        product.setInternet(internetService.findById(internet.getId()));
        productService.saveProduct(product);

        return "redirect:/clients/{clientid}/product/{productid}";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/internet/{internetid}/delete")
    public String deleteInternet(@PathVariable Long internetid,
                                 @PathVariable Long productid){
        Product product = productService.findById(productid);
        product.setInternet(null);
        productService.saveProduct(product);
                return "redirect:/clients/{clientid}/product/{productid}";
    }

    @GetMapping("/clients/{clientid}/product/{productid}/internet/{internetid}/edit")
    public String editInternet(@PathVariable Long internetid,
                               @PathVariable Long productid,
                               @PathVariable Long clientid, Model model){
        model.addAttribute("internet", internetService.findById(internetid));
        model.addAttribute("internets", internetService.findAll());
        model.addAttribute("productid", productid);
        model.addAttribute("clientid", clientid);

        return "choose-internet";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/internet/{internetid}/edit")
    public String editInternet(@PathVariable Long productid, Internet internet){
        Product product = productService.findById(productid);
        product.setInternet(internetService.findById(internet.getId()));
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productid}";
    }

}

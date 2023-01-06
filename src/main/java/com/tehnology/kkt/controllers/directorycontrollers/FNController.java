package com.tehnology.kkt.controllers.directorycontrollers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.extraclasses.FN;
import com.tehnology.kkt.services.FNService;
import com.tehnology.kkt.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class FNController {

    private final ProductService productService;
    private final FNService fnService;


    @GetMapping("/clients/{clientid}/product/{productid}/fn")
    public String fn(@PathVariable Long clientid,
                     @PathVariable Long productid, Model model){
        model.addAttribute("fn", new FN());
        model.addAttribute("clientid", clientid);
        model.addAttribute("productid", productid);
        return "creaters/create-fn";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/fn")
    public String fn(@PathVariable Long productid, FN fn){
        Product product = productService.findById(productid);
        product.setFn(fn);
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productid}";
    }

    @GetMapping("/clients/{clientid}/product/{productid}/fn/{fnid}/edit")
    public String editFn(@PathVariable Long fnid,
                         @PathVariable Long productid,
                         @PathVariable Long clientid, Model model){

        model.addAttribute("fn", fnService.findById(fnid));
        model.addAttribute("productid", productid);
        model.addAttribute("clientid", clientid);

        return "creaters/create-fn";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/fn/{fnid}/edit")
    public String editFN(@PathVariable Long fnid,
                         @PathVariable Long clientid,
                         @PathVariable Long productid, FN fn){
        fn.setId(fnid);
        fnService.save(fn);
        return "redirect:/clients/{clientid}/product/{productid}";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/fn/{fnid}/delete")
    public String deleteFN(@PathVariable Long fnid,
                           @PathVariable Long productid){
        Product product = productService.findById(productid);
        product.setFn(null);
        fnService.deleteById(fnid);
        return "redirect:/clients/{clientid}/product/{productid}";
    }

}

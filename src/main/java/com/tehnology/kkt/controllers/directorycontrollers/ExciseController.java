package com.tehnology.kkt.controllers.directorycontrollers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.enums.Excise;
import com.tehnology.kkt.models.enums.VAT;
import com.tehnology.kkt.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ExciseController {

    private final ProductService productService;

    @PostMapping("/clients/{clientid}/product/{productId}/addexcise")
    public String addVat(@PathVariable Long productId){
        Product product = productService.findById(productId);
        product.setExcise(Excise.Да);
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productId}";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/deleteexcise")
    public String deleteVat(@PathVariable Long productId){
        Product product = productService.findById(productId);
        product.setExcise(null);
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productId}";
    }

}

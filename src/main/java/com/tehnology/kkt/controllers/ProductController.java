package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Description;
import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final DescriptionService descriptionService;
    private final ProductService productService;
    private final UserService userService;
    private final OperatorService operatorService;
    private final TariffService tariffService;

        @GetMapping("/clients/{id}/product")
    public String crateProduct(@PathVariable("id") Long id, Product product, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("product", new Product());
        model.addAttribute("operators", operatorService.findAll());
        model.addAttribute("tariffs", tariffService.findAll());
        model.addAttribute("descriptions", descriptionService.findAll());
        return "create-product";
    }

    @PostMapping("/clients/{id}/product")
    public String crateNewProduct(@PathVariable("id") Long id,Product product) {

        product.setUser(userService.findById(id));
        product.setId(null);
        product.getOfd().setTariff((tariffService.findById(product.getOfd().getTariff().getId())));
        product.getOfd().setOperator(operatorService.findById(product.getOfd().getOperator().getId()));
        product.setDescription(descriptionService.findById(product.getDescription().getId()));
        product.getOfd().setTariff(tariffService.findById(product.getOfd().getTariff().getId()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(product.getOfd().getDateStart());
        calendar.add(Calendar.MONTH, 36);
        product.getOfd().setDayEnd(calendar.getTime());
        productService.saveProduct(product);



        return "redirect:/clients/{id}";
    }
}

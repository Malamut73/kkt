package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.extraclasses.firdirectory.Tariff;
import com.tehnology.kkt.services.ProductService;
import com.tehnology.kkt.services.TariffService;
import com.tehnology.kkt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor

public class TariffController {

    private final TariffService tariffService;
    private final ProductService productService;
    private final UserService userService;


    @GetMapping("/tariffs")
    public String tariff(Model model) {
        model.addAttribute("tariffs", tariffService.findAll());
        return "tariffs";
    }

    @PostMapping("/tariffs")
    public String tariff(Model model, Tariff tariff) {
        tariffService.save(tariff);
        return "redirect:/tariffs";
    }

    @GetMapping("/tariffs/create")
    public String createTariff(Model model) {
        model.addAttribute("tariff", new Tariff());
        return "create-tariff";
    }

    @GetMapping("/tariffs/create/clients/{clientid}/product/{productid}")
    public String createTariffFromOFD(@PathVariable Long clientid,
                                      @PathVariable Long productid,
                                      Model model) {
        model.addAttribute("tariff", new Tariff());
        model.addAttribute("clientid",clientid);
        model.addAttribute("productid", productid);
        return "create-tariff";
    }

    @PostMapping("/tariffs/create/clients/{clientid}/product/{productid}")
    public String returnToOFDCreator(@PathVariable Long productid,
                         @PathVariable Long clientid,
                         Model model, Tariff tariff) {
        tariffService.save(tariff);
        User user = userService.findById(clientid);
        model.addAttribute("user",user);
        return "redirect:/clients/{clientid}/product/{productid}/ofd";

    }

}

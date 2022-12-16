package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.extraclasses.Operator;
import com.tehnology.kkt.models.extraclasses.Tariff;
import com.tehnology.kkt.services.OperatorService;
import com.tehnology.kkt.services.TariffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor

public class TariffController {

    private final TariffService tariffService;

    @GetMapping("/tariffs")
    public String tariff(Model model) {

        model.addAttribute("tariffs", tariffService.findAll());

        return "tariffs";
    }

    @PostMapping("/tariffs")
    public String tariff(Tariff tariff) {

        tariffService.save(tariff);
        return "redirect:/tariffs";
    }

    @GetMapping("/tariffs/create")
    public String tariffCreator(Model model) {

        model.addAttribute("tariff", new Tariff());

        return "create-tariff";
    }

}

package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.extraclasses.Operator;
import com.tehnology.kkt.services.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OperatorController {

    private final OperatorService operatorService;

    @GetMapping("/operator")
    public String operator(Model model) {

        model.addAttribute("operators", operatorService.findAll());

        return "operator";
    }

    @PostMapping("/operator")
    public String operator(Operator operator) {

        operatorService.save(operator);
        return "redirect:/operator";
    }

    @GetMapping("/operator/create")
    public String operatorCreator(Model model) {

        model.addAttribute("operator", new Operator());

        return "create-operator";
    }

}

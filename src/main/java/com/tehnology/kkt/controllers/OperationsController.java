package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Operation;
import com.tehnology.kkt.models.enums.Aim;
import com.tehnology.kkt.services.OperationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OperationsController {

    private final OperationsService operationsService;

    @GetMapping("/operations")
    public String operations(Model model){
        model.addAttribute("operations", operationsService.findOperationByAim(Aim.ПЛАНИРУЕМЫЙ_РАСХОД));
        return "list-operations";
    }

    @PostMapping("/operations/createFeatureExpense")
    public String addFeatureExpense(@ModelAttribute Operation operation){
        operation.setAim(Aim.ПЛАНИРУЕМЫЙ_РАСХОД);
        operationsService.save(operation);
        return "redirect:/operations";
    }
}

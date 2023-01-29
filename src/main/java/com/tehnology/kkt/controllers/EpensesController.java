package com.tehnology.kkt.controllers;

import com.tehnology.kkt.services.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ExpensesController {

    private final ExpensesService expensesService;

    @GetMapping("/exepenses")
    public String expenses(Model model){
        model.addAttribute()
        return "expenses";
    }
}

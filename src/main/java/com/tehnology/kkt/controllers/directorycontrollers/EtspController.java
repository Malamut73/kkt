package com.tehnology.kkt.controllers.directorycontrollers;

import com.tehnology.kkt.models.extraclasses.firdirectory.Etsp;
import com.tehnology.kkt.services.EtspService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class EtspController {

    private final EtspService etspService;

    @GetMapping("/etsp")
    public String listEtcp(Model model){
        model.addAttribute("etsps", etspService.findAll());
        return "etsps";
    }

    @GetMapping("/etsp/create")
    public String etspCreate(Model model){
        model.addAttribute("etsp", new Etsp());
        return "create-etsp";
    }

    @PostMapping("/etsp/create")
    public String etspCreate(Etsp etsp){
        etspService.save(etsp);
        return "redirect:/etsp";
    }
}

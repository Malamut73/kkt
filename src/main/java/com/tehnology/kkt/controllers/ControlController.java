package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.services.FNService;
import com.tehnology.kkt.services.MaintenanceService;
import com.tehnology.kkt.services.OFDService;
import com.tehnology.kkt.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ControlController {

    private final MaintenanceService maintenanceService;
    private final OFDService ofdService;
    private final FNService fnService;

    @GetMapping("/controls/maintenances")
    public String maintenanceList(Model model){

        model.addAttribute("maintenances", maintenanceService.findAllByOrderByDayEndDesc());
        return "control/list-maintenances";
    }

    @GetMapping("/controls/ofds")
    public String ofdList(Model model){
        model.addAttribute("ofds", ofdService.findAllByOrderByDayEndDesc());
        return "control/list-ofds";
    }

    @GetMapping("/controls/fns")
    public String fnList(Model model){
        model.addAttribute("fns", fnService.findAllByOrderByDayEndDesc());
        return "control/list-fns";
    }

}

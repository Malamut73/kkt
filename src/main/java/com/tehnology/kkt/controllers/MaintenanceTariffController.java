package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.MaintenanceTariff;
import com.tehnology.kkt.services.MaintenanceService;
import com.tehnology.kkt.services.MaintenanceTariffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MaintenanceTariffController {

    private final MaintenanceTariffService maintenanceTariffService;
    private final MaintenanceService maintenanceService;

    @GetMapping("/maintenanceTariff")
    public String maintenances(Model model){
        model.addAttribute("maintenanceTariffs", maintenanceTariffService.findAll());
        return "maintenanceTariff";
    }

    @GetMapping("/maintenanceTariff/create")
    public String createMaintenanceTariff(Model model){
        model.addAttribute("maintenanceTariff", new MaintenanceTariff());
        return "create-maintenanceTariff";
    }

    @PostMapping("/maintenanceTariff/create")
    public String createMaintenanceTariff(Model model, MaintenanceTariff maintenanceTariff){
        maintenanceTariffService.save(maintenanceTariff);
        return "redirect:/maintenanceTariff";
    }

//    @GetMapping("/maintenanceTariff/{maintenanceTariffid}/addtrip")
//    public String maintenanceTariffInfo(@PathVariable Long maintenanceTariffid,  Model model){
//        model.addAttribute("maintenanceTariff", maintenanceTariffService.findById(maintenanceTariffid));
//        model.addAttribute("trip", new Trip());
//        return "create-maintenance-addtrip";
//    }
//
//    @PostMapping("/maintenanceTariff/{maintenanceTariffid}/addtrip")
//    public String addTrip(@PathVariable Long maintenanceTariffid, Trip trip){
//        System.out.println(trip.getName());
//        MaintenanceTariff maintenanceTariff = maintenanceTariffService.findById(maintenanceTariffid);
//        maintenanceTariff.getTrips().add(trip);
//        maintenanceTariffService.save(maintenanceTariff);
//        return "redirect:/maintenanceTariff/{maintenanceTariffid}/addtrip";
//
//    }
}

package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.extraclasses.Comment;
import com.tehnology.kkt.models.extraclasses.Maintenance;
import com.tehnology.kkt.models.extraclasses.firdirectory.MaintenanceTariff;
import com.tehnology.kkt.models.extraclasses.firdirectory.Trip;
import com.tehnology.kkt.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;

@Controller
@RequiredArgsConstructor
public class MaintenanceController {

    private final UserService userService;
    private final ProductService productService;
    private final MaintenanceService maintenanceService;
    private final MaintenanceTariffService maintenanceTariffService;
    private final TripService tripService;

    @GetMapping("/clients/{clientid}/product/{productId}/maintenance")
    public String maintenance(@PathVariable Long productId,
                              @PathVariable Long clientid,
                              Maintenance maintenance, Model model) {
        model.addAttribute("productId", productId);
        model.addAttribute("clientid", clientid);
        model.addAttribute("maintenance", new Maintenance());
        model.addAttribute("maintenancetariffs", maintenanceTariffService.findAll());


        return "create-maintenance";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/maintenance")
    public String createMaintenance(@PathVariable Long clientid,
                                    @PathVariable Long productId, Maintenance maintenance) {
        Product product = productService.findById(productId);
        MaintenanceTariff maintenanceTariff = maintenanceTariffService.findByName(maintenance.getName());
        for (int i = 1; i < maintenanceTariff.getMountTrip() + 1; i++) {
            maintenance.getTrips().add(Trip.builder()
                    .name(i)
                    .build());
        }
        maintenance.getComments().add(Comment.builder()
                .text(maintenance.getComment())
                .build());
        product.setMaintenance(maintenance);
        productService.saveProduct(product);

        return "redirect:/clients/{clientid}/product/{productId}";
    }

    @GetMapping("/clients/{clientid}/product/{productId}/maintenance/{maintenanceid}/edit")
    public String editMaintenance(@PathVariable Long clientid,
                                  @PathVariable Long productId,
                                  @PathVariable Long maintenanceid, Model model){
        model.addAttribute("clientid", clientid);
        model.addAttribute("productId", productId);
        model.addAttribute("maintenanceid", maintenanceid);
        model.addAttribute("maintenance", maintenanceService.findById(maintenanceid));
        return "edit-maintenance";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/maintenance/{maintenanceid}/edit")
    public String editMaintenance(Maintenance maintenance){
        maintenanceService.save(maintenance);
        return "redirect:/clients/{clientid}/product/{productId}/";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/maintenance/{maintenanceid}/delete")
    public String deleteMaintanance(@PathVariable Long clientid,
                                    @PathVariable Long productId,
                                    @PathVariable Long maintenanceid, Model model){
        Product product = productService.findById(productId);
        product.setMaintenance(null);
        productService.saveProduct(product);
        maintenanceService.deleteById(maintenanceid);
        model.addAttribute("product", productService.findById(productId));
        return "redirect:/clients/{clientid}/product/{productId}/";
    }

    @GetMapping("/clients/{clientid}/product/{productId}/maintenance/{maintenanceid}/edit/tariff")
    public String editTariff(@PathVariable Long clientid,
                             @PathVariable Long productId,
                             @PathVariable Long maintenanceid, Model model){
        model.addAttribute("maintenancetariffs", maintenanceTariffService.findAll());
        model.addAttribute("clientid", clientid);
        model.addAttribute("productId", productId);
        model.addAttribute("maintenanceid", maintenanceid);
        model.addAttribute("MaintenanceTariff", new MaintenanceTariff());

        return "edit-maintenance-tariff";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/maintenance/{maintenanceid}/edit/tariff")
    public String editTariff(@PathVariable("maintenanceid") Maintenance maintenance,
                             MaintenanceTariff maintenanceTariff){
        MaintenanceTariff maintenanceTariffFromDB = maintenanceTariffService.findById(maintenanceTariff.getId());
        tripService.deleteAll(maintenance.getTrips());
        maintenance.setTrips(new HashSet<>());

        for (int i = 1; i < maintenanceTariffFromDB.getMountTrip() + 1; i++) {
            maintenance.getTrips().
                    add(Trip.builder()
                    .name(i)
                    .build());
        }
        maintenance.setName(maintenanceTariffFromDB.getName());
        maintenanceService.save(maintenance);
        return "redirect:/clients/{clientid}/product/{productId}/";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/maintenance/{maintenanceid}/trip/{tripid}/delete")
    public String editTrip(@PathVariable("tripid") Trip trip){
        trip.setDateTrip(null);
        tripService.save(trip);
        return "redirect:/clients/{clientid}/product/{productId}/";
    }


}

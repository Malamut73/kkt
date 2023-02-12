package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.Maintenance;
import com.tehnology.kkt.models.catalog.MaintenanceTariff;
import com.tehnology.kkt.models.Trip;
import com.tehnology.kkt.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Date;
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
    public String createMaintenance(@PathVariable Long clientid, @RequestParam(name="id", required = false) MaintenanceTariff maintenanceTariff,
                                    @PathVariable("productId") Product product, Maintenance maintenance,
                                    Principal principal) {
        if(maintenanceTariff != null){
            maintenance.setName(maintenanceTariff.getName());
            for (int i = 1; i < maintenanceTariff.getMountTrip() + 1; i++) {
                maintenance.getTrips().add(Trip.builder()
                        .name(i)
                        .build());
            }
        }
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("добавил техобслуживание").build());
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
    public String editMaintenance(@PathVariable("productId") Product product, Principal principal,
                                  Maintenance maintenance){
        product.getComments().add(Comment.builder().user(principal.getName())
                .text(" отредактировал дату техобслуживания").build());
        product.getMaintenance().setDateStart(maintenance.getDateStart());
        product.getMaintenance().setDayEnd(maintenance.getDayEnd());
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productId}/";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/maintenance/{maintenanceid}/delete")
    public String deleteMaintanance(@PathVariable Long clientid, Principal principal,
                                    @PathVariable Long productId,
                                    @PathVariable Long maintenanceid, Model model){
        Product product = productService.findById(productId);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("удалил заявку").build());
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
                             @PathVariable("productId") Product product, Principal principal,
                             @RequestParam(name="id", required = false) MaintenanceTariff maintenanceTariff){
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("отредактировал тариф техобслуживания").build());
        if (maintenanceTariff != null){
            tripService.deleteAll(maintenance.getTrips());
            maintenance.setTrips(new HashSet<>());
            for (int i = 1; i < maintenanceTariff.getMountTrip() + 1; i++) {
                maintenance.getTrips().
                        add(Trip.builder()
                                .name(i)
                                .build());
            }
            maintenance.setName(maintenanceTariff.getName());
            maintenanceService.save(maintenance);
        }
        return "redirect:/clients/{clientid}/product/{productId}/";
    }




}

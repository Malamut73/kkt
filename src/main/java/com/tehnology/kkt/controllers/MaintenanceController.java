package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.extraclasses.Comment;
import com.tehnology.kkt.models.extraclasses.Maintenance;
import com.tehnology.kkt.models.extraclasses.firdirectory.MaintenanceTariff;
import com.tehnology.kkt.models.extraclasses.firdirectory.Trip;
import com.tehnology.kkt.services.MaintenanceService;
import com.tehnology.kkt.services.MaintenanceTariffService;
import com.tehnology.kkt.services.ProductService;
import com.tehnology.kkt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MaintenanceController {

    private final UserService userService;
    private final ProductService productService;
    private final MaintenanceService maintenanceService;
    private final MaintenanceTariffService maintenanceTariffService;

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
        for (Trip trip : maintenanceTariff.getTrips()) {
            product.getMaintenance().getTrips().add(new Trip)
        }

        product.setMaintenance(maintenance);
        Comment comment = Comment.builder()
                .text(product.getMaintenance().getComment())
                .build();
        if(maintenance.getMaintenanceTariff().getId() != null){
            product.getMaintenance().setMaintenanceTariff(
                    maintenanceTariffService.findById(
                            product.getMaintenance().getMaintenanceTariff().getId()));
        }
        product.getMaintenance().getComments().add(
                comment);
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
        model.addAttribute("maintenancetariffs", maintenanceTariffService.findAll());

        return "create-maintenance";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/maintenance/{maintenanceid}/edit")
    public String editMaintenance(@PathVariable Long clientid,
                                  @PathVariable Long productId,
                                  @PathVariable Long maintenanceid, Maintenance maintenance){
        maintenance.setId(maintenanceid);
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


}

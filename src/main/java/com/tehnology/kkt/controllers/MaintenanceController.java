package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.extraclasses.Maintenance;
import com.tehnology.kkt.services.MaintenanceService;
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

    @GetMapping("/clients/{userId}/product/{productId}")
    public String maintenance(@PathVariable("productId") Long productId,
                              @PathVariable("userId") Long userId,
                              Maintenance maintenance, Model model) {
        model.addAttribute("productId", productId);
        model.addAttribute("userId", userId);
        model.addAttribute("maintenance", new Maintenance());


        return "create-maintenance";
    }

    @PostMapping("/clients/{userId}/product/{productId}")
    public String createMaintenance(@PathVariable("productId") Long id, Maintenance maintenance) {

        Product product = productService.findById(id);
        System.out.println(product.getId());
        System.out.println(maintenance.getName());
//        product.getMaintenance().add(maintenance);
        maintenance.setProduct(product);
        maintenanceService.save(maintenance);

        return "redirect:/clients/{userId}";
    }


}

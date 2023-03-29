package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.Request;
import com.tehnology.kkt.models.catalog.ProductEquipment;
import com.tehnology.kkt.services.ProductEquipmentService;
import com.tehnology.kkt.services.ProductService;
import com.tehnology.kkt.services.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductEquipmentController {

    private final ProductEquipmentService productEquipmentService;
    private final RequestService requestService;

    @GetMapping("/equipments")
    public String equipments(Model model){
        model.addAttribute("equipments", productEquipmentService.findAll());
        return "equipment/equipments";
    }

    @GetMapping("/equipments/create")
    public String addEquipments(){
        return "equipment/create-equipment";
    }

    @PostMapping("/equipments/create")
    public String addEquipments(@ModelAttribute ProductEquipment productEquipment){
        productEquipmentService.save(productEquipment);
        return "redirect:/equipments";

    }

    @GetMapping("/equipments/{equipmentid}/delete")
    public String deleteEquipments(@PathVariable("equipmentid") ProductEquipment productEquipment){
        productEquipmentService.delete(productEquipment);
        return "redirect:/equipments";
    }

    @GetMapping("/request/{requestid}/productEquipment")
    public String addTypeOfActivity(@PathVariable("requestid") Request request,
                                    Model model){
        model.addAttribute("productEquipments", productEquipmentService.findAll());
        model.addAttribute("request", request);
        return "equipment/choose-equipment";
    }

    @PostMapping("/request/{requestid}/productEquipment")
    public String addTypeOfActivity(@RequestParam(name = "equip[]", required = false) String[] equip,
                                    @PathVariable("requestid") Request request, Principal principal){
        if(equip != null) requestService.setProductEquipment(request, equip, principal);
        return "redirect:/request/{requestid}";
    }

}

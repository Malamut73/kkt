package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Store;
import com.tehnology.kkt.services.StoresService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class StoreController {

    private final StoresService storesService;

    @GetMapping("/store")
    public String stores(Model model){
        model.addAttribute("stores", storesService.findAll());
        return "list-stores";
    }

    @GetMapping("/store/add")
    public String newStore(){
        return "create-store";
    }

    @PostMapping("/store/add")
    public String addStore(@ModelAttribute Store store){
        storesService.save(store);
        return "redirect:/store";
    }

    @GetMapping("/store/{storeid}")
    public String editStore(@PathVariable("storeid") Store store, Model model){
        model.addAttribute("store", store);
        return "info-store";
    }

    @PostMapping("/store/{storeid}")
    public String editStore(Store store){
        storesService.save(store);
        return "redirect:/store";
    }
}

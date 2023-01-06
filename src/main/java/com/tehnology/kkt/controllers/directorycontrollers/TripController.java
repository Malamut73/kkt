package com.tehnology.kkt.controllers.directorycontrollers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.extraclasses.firdirectory.Trip;
import com.tehnology.kkt.services.ProductService;
import com.tehnology.kkt.services.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;
    private final ProductService productService;

    @GetMapping("/clients/{clientid}/product/{productId}/maintenanceTariff/{maintenanceTariffid}/trip/{tripid}")
    public String editTrip(@PathVariable Long tripid,
                           @PathVariable Long productId,
                           @PathVariable Long clientid,
                           @PathVariable Long maintenanceTariffid, Model model){
        model.addAttribute("trip", tripService.findById(tripid));
        model.addAttribute("clientid", clientid);
        model.addAttribute("productId", productId);
        model.addAttribute("maintenanceTariffid", maintenanceTariffid);
        return "edit-trip";
    }

    @PostMapping("/clients/{clientid}/product/{productId}/maintenanceTariff/{maintenanceTariffid}/trip/{tripid}")
    public String editTrip(
                           @PathVariable Long tripid, Trip trip, Model model){
        trip.setId(tripid);
        tripService.save(trip);
        return "redirect:/clients/{clientid}/product/{productid}";
    }


}

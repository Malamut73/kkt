package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.*;
import com.tehnology.kkt.models.catalog.MaintenanceTariff;
import com.tehnology.kkt.models.enums.Condit;
import com.tehnology.kkt.models.enums.Egais;
import com.tehnology.kkt.models.enums.Excise;
import com.tehnology.kkt.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class FillProductController {

    private final DescriptionService descriptionService;
    private final ProductService productService;
    private final LKSiteService lkSiteService;
    private final InternetService internetService;
    private final OperatorService operatorService;
    private final TariffService tariffService;
    private final MaintenanceTariffService maintenanceTariffService;
    private final ProductMarkService productMarkService;
    private final TypeOfActivityService typeOfActivityService;
    private final VatService vatService;
    private final TaxationService taxationService;

    @GetMapping("/fill/clients/{userid}/product")
    public String crateProduct(@PathVariable("userid") Long clientid, Model model) {
        model.addAttribute("clientid", clientid);
        model.addAttribute("descriptions", descriptionService.findAll());
        model.addAttribute("conditions", Condit.values());
        return "fillproduct/product";
    }

    @PostMapping("/fill/clients/{clientid}/product")
    public String crateProduct(@PathVariable("clientid") User user,
                               @ModelAttribute Product product,
                               Principal principal, Model model) {
        product.getComments().add(Comment.builder()
                .text("создал кассу").user(principal.getName()).build());
        product.setUser(user);
        Product newProduct = productService.saveAndflush(product);
        model.addAttribute("product", newProduct);
        model.addAttribute("lksites", lkSiteService.findAll());
        return "fillproduct/lk";
    }

    @PostMapping("/fill/clients/{clientid}/product/{productid}/lk")
    public String addLK(@PathVariable Long clientid, Principal principal,
                        @PathVariable Long productid, Model model,
                        @ModelAttribute LK lk){
        Product product = productService.findById(productid);
        product.setLk(lk);
        product.getComments().add(Comment.builder()
                .user(principal.getName()).text("добавил данные в личный кабинет").build());
        Product newProduct = productService.saveAndflush(product);
        model.addAttribute("product", newProduct);
        model.addAttribute("tariffs", tariffService.findAll());


        return "fillproduct/fn";
    }

    @PostMapping("/fill/clients/{clientid}/product/{productid}/fn")
    public String addFn(@PathVariable Long productid, Principal principal,
                        @ModelAttribute FN fn, Model model){
        Product product = productService.findById(productid);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("создал фн").build());
        product.setFn(fn);
        Product newProduct = productService.saveAndflush(product);
        model.addAttribute("internets", internetService.findAll());
        model.addAttribute("product", newProduct);

        return "fillproduct/internet";
    }

    @PostMapping("/fill/clients/{clientid}/product/{productid}/internet")
    public String addInternet(@PathVariable("productid") Product product, Principal principal,
                              @ModelAttribute SimCard simCard, Model model ){
        product.setSimCard(simCard);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("добавил сим-карту").build());
        Product newProduct = productService.saveAndflush(product);
        model.addAttribute("product", newProduct);
        model.addAttribute("tariffs", tariffService.findAll());
        model.addAttribute("operators", operatorService.findAll());

        return "fillproduct/ofd";
    }

    @PostMapping("/fill/clients/{clientid}/product/{productid}/ofd")
    public String addOFD(@PathVariable("clientid") User user, Principal principal,
                         @PathVariable("productid") Product product,
                         Model model, @ModelAttribute OFD ofd){
        product.setOfd(ofd);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("добавил офд").build());
        Product newProduct = productService.saveAndflush(product);
        model.addAttribute("maintenance", new Maintenance());
        model.addAttribute("maintenancetariffs", maintenanceTariffService.findAll());
        model.addAttribute("product", newProduct);

        return "fillproduct/maintenance";
    }

    @PostMapping("/fill/clients/{clientid}/product/{productId}/maintenance")
    public String createMaintenance(@PathVariable Long clientid, @RequestParam(name="maintenanceTariff", required = false) String name,
                                    @PathVariable("productId") Product product, @ModelAttribute Maintenance maintenance,
                                    @RequestParam("text") String comment, Principal principal, Model model) {
        MaintenanceTariff maintenanceTariff = maintenanceTariffService.findByName(name);
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
        product.getComments().add(Comment.builder().user(principal.getName())
                .text(comment).build());
        product.setMaintenance(maintenance);
        Product newProduct = productService.saveAndflush(product);
        model.addAttribute("marks", productMarkService.findAll());
        model.addAttribute("product", newProduct);
        return "fillproduct/mark";
    }

    @PostMapping("/fill/clients/{clientid}/product/{productid}/productMark")
    public String addMarkToProduct(@RequestParam(name = "marks[]", required = false) String[] marks, Principal principal,
                                   @PathVariable("productid") Product product, Model model){
        Set<String> productMarks = null;
        if (marks != null){
            productMarks = new HashSet<>(Arrays.asList(marks));
            product.setProductMark(productMarks);
        }
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("изменил маркировку").build());

        Product newProduct = productService.saveAndflush(product);
        model.addAttribute("product", newProduct);
        model.addAttribute("typeofactivities", typeOfActivityService.findAll());

        return "fillproduct/typeofactivity";
    }

    @PostMapping("/fill/clients/{clientid}/product/{productId}/typeofactivity")
    public String addTypeOfActivity(@RequestParam(name = "typeof[]", required = false) String[] typeof, Model model,
                                    @PathVariable("productId") Product product, Principal principal){
        Set<String> typeOfActivities = null;
        if (typeof != null){
            typeOfActivities = new HashSet<>(Arrays.asList(typeof));
            product.setTypeOfActivities(typeOfActivities);

        }
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("изменил вид деятельности").build());
        Product newProduct = productService.saveAndflush(product);
        model.addAttribute("product", newProduct);
        model.addAttribute("vats", vatService.findAll());
        model.addAttribute("taxations", taxationService.findAll());
        return "fillproduct/vat";
    }

    @PostMapping("/fill/clients/{clientid}/product/{productId}/addvat")
    public String addVat(@RequestParam("vat") String vat, Principal principal,
                         @RequestParam(name ="name", required = false) String name,
                         @RequestParam(name = "egais", required = false) String egais,
                         @RequestParam(name = "excise", required = false) String excise,
                         @PathVariable("productId") Product product){
        product.setVat(vat);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("добавил ставку НДС").build());
        if(name != null) product.setTaxation(name);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("изменил налогооблажение").build());
        if(egais != null) product.setEgais(Egais.valueOf(egais));
        if(excise != null) product.setExcise(Excise.valueOf(excise));
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productId}";
    }



}

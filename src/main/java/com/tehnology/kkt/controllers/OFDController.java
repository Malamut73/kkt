package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.OFD;
import com.tehnology.kkt.services.OFDService;
import com.tehnology.kkt.services.OperatorService;
import com.tehnology.kkt.services.ProductService;
import com.tehnology.kkt.services.TariffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Calendar;

@Controller
@RequiredArgsConstructor
public class OFDController {

    private final ProductService productService;
    private final OFDService ofdService;
    private final TariffService tariffService;
    private final OperatorService operatorService;


    @GetMapping("/clients/{clientid}/product/{productid}/ofd")
    public String addOFD(@PathVariable Long clientid,
                         @PathVariable Long productid, Model model){
        model.addAttribute("ofd", new OFD());
        model.addAttribute("tariffs", tariffService.findAll());
        model.addAttribute("operators", operatorService.findAll());
        model.addAttribute("clientid", clientid);
        model.addAttribute("productid", productid);
        return "create-ofd";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/ofd")
    public String addOFD(@PathVariable("clientid") User user, Principal principal,
                         @PathVariable("productid") Product product,
                         Model model, OFD ofd){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ofd.getDateStart());
            calendar.add(Calendar.MONTH, ofd.getDays());
            ofd.setDayEnd(calendar.getTime());
        product.setOfd(ofd);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("добавил офд").build());
        productService.saveProduct(product);

        return "redirect:/clients/{clientid}/product/{productid}";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/ofd/{ofdid}/delete")
    public String deleteOFD(@PathVariable Long clientid,
                            @PathVariable Long productid,
                            @PathVariable Long ofdid,
                            OFD ofd, Principal principal){


        Product product = productService.findById(productid);
        product.setOfd(null);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("удалил офд").build());
        productService.saveProduct(product);
        ofdService.deleteOFDById(ofdid);
        return "redirect:/clients/{clientid}/product/{productid}";
    }

    @GetMapping("/clients/{clientid}/product/{productid}/ofd/{ofdid}/edit")
    public String editOfd(@PathVariable Long clientid,
                         @PathVariable Long productid,
                         @PathVariable Long ofdid,Model model){
        model.addAttribute("ofd", ofdService.findById(ofdid));
        model.addAttribute("tariffs", tariffService.findAll());
        model.addAttribute("operators", operatorService.findAll());
        model.addAttribute("clientid", clientid);
        model.addAttribute("productid", productid);
        model.addAttribute("ofdid", productid);


        return "create-ofd";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/ofd/{ofdid}/edit")
    public String editOfd(@PathVariable Long clientid, Principal principal,
                         @PathVariable("productid") Product product,
                         OFD ofd, Model model){
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("изменил офд").build());
        product.setOfd(ofd);
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productid}";
    }



}

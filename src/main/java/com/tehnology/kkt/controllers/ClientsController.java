package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.extraclasses.Comment;
import com.tehnology.kkt.models.extraclasses.Maintenance;
import com.tehnology.kkt.services.ProductService;
import com.tehnology.kkt.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class ClientsController {

    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/clients")
    public String clients(Model model) {

        model.addAttribute("clients", userService.findAll());

        return "clients";
    }

    @PostMapping("/clients")
    public String clients() {
        return "clients";
    }

    @GetMapping("/clients/create")
    public String createClient(Model model) {
        model.addAttribute("user", new User());
        return "create-client";
    }

    @PostMapping("/clients/create")
    public String createClient(User user, Principal principal, Model model) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        Comment comment = Comment.builder()
                .text(formater.format(today) + " " + principal.getName() + " создал клиента.")
                .build();
        user.getComments().add(comment);
        userService.saveClient(user);
        return "redirect:/clients";
    }

    @GetMapping("/clients/{id}")
    public String userInfo(@PathVariable("id") Long id, Model model) {

        User user = userService.findById(id);
        Product lastOFD = productService.findLastOFD(user);


        model.addAttribute("lastOFD", lastOFD);
        model.addAttribute("user", user);

//        model.addAttribute("products", productService.findByUser());
        return "client-info";
    }
}

package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.services.OrganizationService;
import com.tehnology.kkt.services.ProductService;
import com.tehnology.kkt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class ClientsController {

    private final UserService userService;
    private final ProductService productService;
    private final OrganizationService organizationService;

    @GetMapping("/clients")
    public String clients(Model model) {
        model.addAttribute("clients", userService.findAllClients());
        return "clients";
    }

    @PostMapping("/clients")
    public String clients() {
        return "clients";
    }

    @GetMapping("/clients/create")
    public String createClient(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("organizations", organizationService.findAll());
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
        user.getRequisite().setOrganization(organizationService.findById(user.getRequisite().getOrganization().getId()));
        userService.saveClient(user);
        return "redirect:/clients";
    }

    @GetMapping("/clients/{clientid}")
    public String userInfo(@PathVariable Long clientid, Model model) {
        User user = userService.findById(clientid);
        model.addAttribute("user", user);

        return "info-client";
    }
}

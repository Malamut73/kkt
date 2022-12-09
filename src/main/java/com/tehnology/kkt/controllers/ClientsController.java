package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.enums.Role;
import com.tehnology.kkt.models.extraclasses.Comment;
import com.tehnology.kkt.models.extraclasses.Requisite;
import com.tehnology.kkt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClientsController {

    private final UserService userService;

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
        LocalDateTime today = LocalDateTime.now();
        Comment comment = Comment.builder()
                .text(today.toString() + " " + principal.getName() + " создал клиента.")
                .build();
        user.getComments().add(comment);
        userService.saveClient(user);
        return "redirect:/clients";
    }

    @GetMapping("/clients/{id}")
    public String userInfo(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "client-info";
    }
}

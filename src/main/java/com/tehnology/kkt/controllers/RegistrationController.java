package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.User;
import com.tehnology.kkt.repositories.UserDAO;
import com.tehnology.kkt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user,
                             Model model){
        userService.create(user);
        return "redirect:/login";
    }
}

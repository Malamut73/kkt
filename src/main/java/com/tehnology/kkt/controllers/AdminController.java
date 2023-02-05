package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.enums.Role;
import com.tehnology.kkt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('Administrator')")
    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("managers", userService.findAllManagers());
        model.addAttribute("administrators", userService.findAllAdministrator());
        return "admin";
    }

    @GetMapping("/admin/{managerid}")
    public String managerInfo(@PathVariable("managerid") User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "info-manager";
    }

    @GetMapping("/admin/create")
    public String createManager(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("roles", Role.values());


        return "create-manager";
    }

    @PostMapping("/admin/edit")
    public String managerSave(User user){

        userService.editStaff(user);

        return "redirect:/admin";
    }

    @PostMapping("/admin/create")
    public String createManager(User user){
        userService.createManager(user);
        return "redirect:/admin";

    }
}

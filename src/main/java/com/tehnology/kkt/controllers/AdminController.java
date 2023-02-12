package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.enums.Role;
import com.tehnology.kkt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
//    @PreAuthorize("hasAuthority('Administrator')")
    @GetMapping("/admin")
    public String admin(Model model, Principal principal){
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        return "admin/admin";
    }

    @PreAuthorize("hasAuthority('Administrator')")
    @GetMapping("/admin/list")
    public String listStaff(Model model){
        model.addAttribute("managers", userService.findAllManager());
        model.addAttribute("administrators", userService.findAllAdministrator());
        return "admin/list-staff";
    }

    @PreAuthorize("hasAuthority('Administrator')")
    @GetMapping("/admin/{managerid}/info")
    public String managerInfo(@PathVariable("managerid") User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "admin/info-manager";
    }

    @PostAuthorize("isAuthenticated() and #useremail == authentication.principal.username")
    @GetMapping("/admin/{useremail}")
    public String editManager(@PathVariable("useremail") String useremail, Model model){
        model.addAttribute("user", userService.findByEmail(useremail));
        model.addAttribute("roles", Role.values());
        return "admin/edit-manager";
    }

    @PostAuthorize("isAuthenticated() and #useremail == authentication.principal.username")
    @PostMapping("/admin/{useremail}")
    public String editManager(@PathVariable("useremail") String useremail, User user){
        User userDB = userService.findByEmail(useremail);
        userService.editStaff(userDB, user);

        return "redirect:/admin";
    }

    @PreAuthorize("hasAuthority('Administrator')")
    @GetMapping("/admin/create")
    public String createManager(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("roles", Role.values());

        return "admin/create-manager";
    }

    @PreAuthorize("hasAuthority('Administrator')")
    @PostMapping("/admin/edit")
    public String managerSave(User user){
        userService.saveStaff(user);
        return "redirect:/admin/list";
    }

    @PreAuthorize("hasAuthority('Administrator')")
    @PostMapping("/admin/create")
    public String createManager(User user){
        userService.createManager(user);
        return "redirect:/admin/list";

    }
}

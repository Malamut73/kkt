package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.enums.Role;
import com.tehnology.kkt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "admin/list-staff";
    }

    @PreAuthorize("hasAuthority('Administrator')")
    @GetMapping("/admin/{managerid}/info")
    public String managerInfo(@PathVariable("managerid") User user, Model model){
        model.addAttribute("user", user);
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
        return "admin/create-manager";
    }

    @PreAuthorize("hasAuthority('Administrator')")
    @PostMapping("/admin/{userid}/edit")
    public String managerSave(@PathVariable("userid") User userFromDB, User user){
        userFromDB.setName(user.getName());
        userFromDB.setLastName(user.getLastName());
        userFromDB.setPatronymic(user.getPatronymic());
        userFromDB.setEmail(user.getEmail());
        userFromDB.setPhoneNumber(user.getPhoneNumber());
        userFromDB.setActive(user.isEnabled());
        userService.saveStaff(userFromDB);
        return "redirect:/admin/list";
    }

    @PreAuthorize("hasAuthority('Administrator')")
    @PostMapping("/admin/create")
    public String createManager(@ModelAttribute User user){
        user.getRoles().add(Role.Manager);
        userService.createManager(user);
        return "redirect:/admin/list";

    }

    @PreAuthorize("hasAuthority('Administrator')")
    @GetMapping("/admin/{managerid}/pass")
    public String sendPass(@PathVariable("managerid") User user){
        userService.createManager(user);
        return "redirect:/admin/list";
    }

    @PostAuthorize("isAuthenticated() and #user.email == authentication.principal.username")
    @GetMapping("/admin/{managerid}/newPass")
    public String changePass(@PathVariable("managerid") User user, Model model){
        model.addAttribute("managerid", user.getId());
        return "admin/new-pass";
    }

    @PostAuthorize("isAuthenticated() and #user.email == authentication.principal.username")
    @PostMapping("/admin/{managerid}/newPass")
    public String changePass(@RequestParam("pass") String pass, Model model,
                             @RequestParam("repeatPass") String repeatPass,
                             @PathVariable("managerid") User user){
        String finalMessage = null;
        if (pass.equals(repeatPass)){
            user.setPassword(pass);
            userService.changePass(user);
            finalMessage = "Пароль успешно сохранен";
        }else{
            finalMessage = "Пароли не совпадают";
        }
        model.addAttribute("user", user);
        model.addAttribute("result", finalMessage);
        return "admin/result-change-pass";
    }
}

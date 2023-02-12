package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.enums.Status;
import com.tehnology.kkt.services.OrganizationService;
import com.tehnology.kkt.services.ProductService;
import com.tehnology.kkt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "user/clients";
    }

    @PostMapping("/clients")
    public String clients(@RequestParam(name = "search", required = false) String search,
                          @RequestParam(name = "text", required = false) String text, Model model) {
        model.addAttribute("clients", userService.findAllClientsBy(text, search));
        return "user/clients";
    }

    @GetMapping("/clients/create")
    public String createClient(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("organizations", organizationService.findAll());
        return "user/create-client";
    }

    @PostMapping("/clients/create")
    public String createClient(User user, Principal principal, Model model) {
        user.setStatus(Status.Новый);
        userService.saveClient(user);
        return "redirect:/clients";
    }

    @GetMapping("/clients/{clientid}")
    public String userInfo(@PathVariable Long clientid, Model model) {
        User user = userService.findById(clientid);
        model.addAttribute("user", user);
        return "user/info-client";
    }

    @GetMapping("/clients/sort")
    public String clientSort(@RequestParam("name") String name, Model model){
        model.addAttribute("clients", userService.findAllByName(name));
        return "user/clients";
    }

    @GetMapping("/clients/{clientid}/comment")
    public String comment(@PathVariable("clientid") User user, Model model){
        model.addAttribute("user", user);
        return "user/comment";
    }

    @PostMapping("/clients/{clientid}/comment")
    public String comment(@PathVariable("clientid") User user,
                          @RequestParam("comment") String comment){
        user.setComment(comment);
        userService.saveClient(user);
        return "redirect:/clients/{clientid}";
    }

    @GetMapping("/clients/{clientid}/edit")
    public String editClient(@PathVariable("clientid") User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("statuses", Status.values());
        model.addAttribute("organizations", organizationService.findAll());
        return "user/edit-client";
    }

    @PostMapping("/clients/{clientid}/edit")
    public String editClient(@PathVariable("clientid") User userFromDB, User user){
        userService.updateUser(userFromDB, user);
        return "redirect:/clients/{clientid}";
    }


}

package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestController {


    @GetMapping("/clients/{user.id}/request")
    public String createRequest(Model model){

        model.addAttribute("request", new Request());
        return "create-request";
    }

}

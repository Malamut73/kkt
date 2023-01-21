package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Request;
import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.enums.Etsp;
import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;


@Controller
@RequiredArgsConstructor
public class RequestController {

    private final TopicService topicService;
    private final ProductService productService;
    private final UserService userService;
    private final RequestService requestService;

    @GetMapping("/requests")
    public String requests(Model model){
        model.addAttribute("requests", requestService.findAll());
        return "requests";
    }

    @GetMapping("/clients/{clientid}/request")
    public String createRequest(@PathVariable Long clientid, Model model){
        model.addAttribute("topics", topicService.findAll());
        model.addAttribute("request", new Request());
        model.addAttribute("clientid", clientid);
        model.addAttribute("etsps", Etsp.values());

        return "create-request";
    }

    @PostMapping("/clients/{clientid}/request")
    public String createRequest(@PathVariable("clientid") User user, Request request){
        request.setTopic(topicService.findById(request.getTopic().getId()));
        request.setActive(true);
        request.setClient(user);
        request.getComments().add(Comment.builder()
                .text(request.getComment())
                .build());
        requestService.saveRequest(request);
        return "redirect:/clients/{clientid}";
    }

    @GetMapping("/clients/{clientid}/request/{requestid}")
    public String requestInfo(@PathVariable Long clientid,
                              @PathVariable("requestid") Request request, Model model){

        model.addAttribute("topics", topicService.findAll());
        model.addAttribute("request",request);
        model.addAttribute("clientid", clientid);

        return "info-request";
    }

    @GetMapping("/clients/{clientid}/request/{requestid}/addetsp")
    public String addEtsp(@PathVariable Long clientid,
                          @PathVariable Long requestid, Model  model){
        model.addAttribute("etsps", Etsp.values());
        model.addAttribute("clientid", clientid);
        model.addAttribute("requestid", requestid);
        model.addAttribute("request", requestService.findById(requestid));
        return "choose-etsp";
    }

    @PostMapping("/clients/{clientid}/request/{requestid}/addetsp")
    public String addEtsp(@PathVariable("requestid") Request request,
                          @RequestParam("etsp") String etsp){
        request.setEtsp(Etsp.valueOf(etsp));
        requestService.saveRequest(request);
        return "redirect:/clients/{clientid}/request/{requestid}";
    }

    @PostMapping("/clients/{clientid}/request/{requestid}/addcomment")
    public String addComment(@PathVariable("requestid") Request request,
                             @ModelAttribute("comment") Comment comment){
        request.getComments().add(comment);
        requestService.saveRequest(request);
        return "redirect:/clients/{clientid}/request/{requestid}";
    }

    @PostMapping("/clients/{clientid}/request/{requestid}/closerequest")
    public String closeRequest(@PathVariable("requestid") Request request){
        request.setActive(false);
        request.setDateOfEnd(new Date(new java.util.Date().getTime()));
        requestService.saveRequest(request);
        return "redirect:/clients/{clientid}/request/{requestid}";
    }

    @PostMapping("/clients/{clientid}/request/{requestid}/delete")
    public String deleteRequest(@PathVariable("requestid") Request request){
        requestService.deleteRequest(request);
        return "redirect:/clients/{clientid}";
    }

}

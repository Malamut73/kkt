package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.Request;
import com.tehnology.kkt.models.extraclasses.Comment;
import com.tehnology.kkt.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.yaml.snakeyaml.events.Event;

@Controller
@RequiredArgsConstructor
public class RequestController {

    private final TopicService topicService;
    private final ProductService productService;
    private final UserService userService;
    private final RequestService requestService;
    private final EtspService etspService;

    @GetMapping("/clients/{clientid}/request")
    public String createRequest(@PathVariable Long clientid, Model model){
        model.addAttribute("products", productService.findAll());
        model.addAttribute("userid", clientid);
        model.addAttribute("topics", topicService.findAll());
        model.addAttribute("etsps", etspService.findAll());
        model.addAttribute("request", new Request());
        model.addAttribute("comment", new Comment());
        return "create-request";
    }

    @PostMapping("/clients/{clientid}/request")
    public String createRequest(@PathVariable Long clientid, Comment comment, Request request){
        request.setTopic(topicService.findById(request.getTopic().getId()));
        request.setProduct(productService.findById(request.getProduct().getId()));
        request.setClient(userService.findById(clientid));
        request.getComments().add(comment);
        request.setEtsp(etspService.findById(request.getEtsp().getId()));
        requestService.saveRequest(request);
        return "redirect:/clients/{userid}";



    }

}

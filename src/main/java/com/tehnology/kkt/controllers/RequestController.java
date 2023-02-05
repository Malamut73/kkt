package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Request;
import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.catalog.Organization;
import com.tehnology.kkt.models.catalog.Topic;
import com.tehnology.kkt.models.enums.Etsp;
import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class RequestController {

    private final TopicService topicService;
    private final ProductService productService;
    private final UserService userService;
    private final RequestService requestService;
    private final OrganizationService organizationService;

    @GetMapping("/requests/{value}")
    public String requests(@PathVariable(name = "value", required = false) String value,
                                       Model model){
        model.addAttribute("requests", requestService.findAllBy(value));
        return "request/requests";
    }

    @GetMapping("/request")
    public String createRequest(Model model){
        model.addAttribute("topics", topicService.findAll());
        model.addAttribute("request", new Request());
        model.addAttribute("etsps", Etsp.values());

        return "request/create-new-request";
    }

    @PostMapping("/request")
    public String createRequest(Request request){
        request.setActive(true);
        requestService.saveRequest(request);
        return "redirect:/requests/true";
    }

    @GetMapping("/clients/{clientid}/request")
    public String createRequest(@PathVariable Long clientid, Model model){
        model.addAttribute("topics", topicService.findAll());
        model.addAttribute("request", new Request());
        model.addAttribute("clientid", clientid);
        model.addAttribute("etsps", Etsp.values());

        return "request/create-request";
    }

    @PostMapping("/clients/{clientid}/request")
    public String createRequest(@PathVariable("clientid") User user, Request request){
        request.setActive(true);
        request.setClient(user);
        request.getComments().add(Comment.builder()
                .text(request.getComment())
                .build());
        requestService.saveRequest(request);
        return "redirect:/clients/{clientid}";
    }

    @GetMapping("/clients/{clientid}/request/{requestid}")
    public String requestInfo(@PathVariable("clientid") Long clientid,
                              @PathVariable("requestid") Request request, Model model){

        model.addAttribute("topics", topicService.findAll());
        model.addAttribute("request",request);
        model.addAttribute("clientid", clientid);

        return "request/info-request";
    }

    @GetMapping("/request/{requestid}/addetsp")
    public String addEtsp(@PathVariable("requestid") Long requestid, Model  model){
        model.addAttribute("etsps", Etsp.values());
        model.addAttribute("requestid", requestid);
        return "request/choose-etsp";
    }

    @PostMapping("/request/{requestid}/addetsp")
    public String addEtsp(@PathVariable("requestid") Request request,
                          @RequestParam(name = "etsp") String etsp){
        request.setEtsp(Etsp.valueOf(etsp));
        requestService.saveRequest(request);
        return "redirect:/request/{requestid}";
    }

    @PostMapping("/request/{requestid}/addcomment")
    public String addComment(@PathVariable("requestid") Request request,
                             @ModelAttribute("comment") Comment comment){
        request.getComments().add(comment);
        requestService.saveRequest(request);
        return "redirect:/request/{requestid}";
    }

    @PostMapping("/request/{requestid}/closerequest")
    public String closeRequest(@PathVariable("requestid") Request request,
                               Principal principal){
        Comment comment = Comment.builder()
                .text(principal.getName() + " закрыл заявку")
                .build();
        request.getComments().add(comment);
        request.setActive(false);
        request.setDateOfEnd(new Timestamp(new java.util.Date().getTime()));
        requestService.saveRequest(request);
        return "redirect:/request/{requestid}";
    }

    @PostMapping("/clients/{clientid}/request/{requestid}/delete")
    public String deleteRequest(@PathVariable("requestid") Request request){
        requestService.deleteRequest(request);
        return "redirect:/clients/{clientid}";
    }

    @GetMapping("/request/{requestid}")
    public String requestInfo(@PathVariable("requestid") Request request, Model model){
        model.addAttribute("request", request);
        return "request/info-request";
    }

    @GetMapping("/request/{requestid}/addClient")
    public String requestAddClient(@PathVariable("requestid") Long requestid,
                                   Model model){
        model.addAttribute("clients", userService.findAllClients());
        model.addAttribute("requestid", requestid);
        return "request/request-add-client";
    }

    @PostMapping("/request/{requestid}/addClient")
    public String requestAddClient(@PathVariable("requestid") Long requestid,
                                   @RequestParam(name = "text", required = false) String text,
                                   @RequestParam(name = "search", required = false) String search,
                                   Model model){

        model.addAttribute("clients", userService.findAllClientsBy(text, search));
        model.addAttribute("requestid", requestid);
        return "request/request-add-client";
    }

    @GetMapping("/request/{requestid}/addClient/{clientid}")
    public String requestAddClient(@PathVariable("requestid") Request request,
                                   @PathVariable("clientid") User user ){
        request.setClient(user);
        requestService.saveRequest(request);
        return "redirect:/request/{requestid}";
    }

    @GetMapping("/request/{requestid}/clients/create")
    public String requestCreateClient(@PathVariable("requestid") Long requestid, Model model){
//        User user = new User();
//        System.out.println("before new user");
//        System.out.println(user.getId());
//        model.addAttribute("user", user);
        model.addAttribute("organizations", organizationService.findAll());
        model.addAttribute("requestid", requestid);
        return "request/request-create-client";
    }

    @PostMapping("/request/{requestid}/clients/create")
    public String requestCreateClient(
                                      @PathVariable("requestid") Request request,
                                      @ModelAttribute User user, Model model){
        System.out.println("before first user id");
        System.out.println(user.getId());
//        user.getRequisite().setOrganization(organization);
        userService.saveClient(user);

        User user2 = userService.findClientBy(
                user.getLastName(), user.getName(), user.getNameOfOrganization()
        );
        System.out.println("before user id");
        System.out.println(user2.getId());
        System.out.println("after user id");

        request.setClient(userService.findClientBy(
                user.getLastName(), user.getName(), user.getNameOfOrganization()
                )
        );
        requestService.saveRequest(request);
//        model.addAttribute("client", userService.findClientBy(
//                user.getLastName(), user.getName(), user.getNameOfOrganization()
//        ));
//        model.addAttribute("requestid", request.getId());
        return "redirect:/request/{requestid}";
    }

    @GetMapping("/request/{requestid}/topic")
    public String changeTopic(@PathVariable("requestid") Long requestid,
                              Model model){
        model.addAttribute("requestid", requestid);
        model.addAttribute("topics", topicService.findAll());
        return "request/change-topic";
    }

    @PostMapping("/request/{requestid}/topic")
    public String changeTopic(@RequestParam(name = "topic", required = false) String topic,
                              @PathVariable("requestid") Request request){
        request.setTopic(topic);
        requestService.saveRequest(request);
        return "redirect:/request/{requestid}";

    }

    @GetMapping("/request/{requestid}/changeContactInfo")
    public String changeContactInfo(@PathVariable("requestid") Request request,  Model model){
        model.addAttribute("request", request);
        return "request/edit-contact-info";
    }

    @PostMapping("/request/{requestid}/changeContactInfo")
    public String caveContactInfo(@RequestParam("nameOfContact") String nameOfContact,
                                  @RequestParam("phoneOfContact") String phoneOfContact,
                                  @PathVariable("requestid") Request request){
        request.setNameOfContact(nameOfContact);
        request.setPhoneOfContact(phoneOfContact);
        requestService.saveRequest(request);
        return "redirect:/request/{requestid}";
    }
}

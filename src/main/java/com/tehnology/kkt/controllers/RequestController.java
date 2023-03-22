package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.Request;
import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.catalog.Organization;
import com.tehnology.kkt.models.catalog.Topic;
import com.tehnology.kkt.models.enums.Condit;
import com.tehnology.kkt.models.enums.Etsp;
import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.enums.Status;
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
    private final DescriptionService descriptionService;
    private final ProductEquipmentService productEquipmentService;

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
    public String createRequest(@RequestParam("comment") String newComment,
                                Request request, Principal principal){

        request.getComments().add(Comment.builder()
                .text(newComment)
                .user(principal.getName()).build());
        request.setActive(true);
        Comment comment = Comment.builder()
                .text("создал заявку")
                .user(principal.getName()).build();
        request.getComments().add(comment);
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
    public String createRequest(@PathVariable("clientid") User user,
                                @RequestParam("comment") String newComment,
                                Request request, Principal principal){
        request.setActive(true);
        request.setClient(user);
        request.getComments().add(Comment.builder()
                .text(newComment)
                .user(principal.getName())
                .build());
        Comment comment = Comment.builder()
                .text("создал заявку")
                .user(principal.getName())
                .build();
        request.getComments().add(comment);
        requestService.saveRequest(request);
        return "redirect:/clients/{clientid}";
    }

    @GetMapping("/clients/{clientid}/request/{requestid}")
    public String requestInfo(@PathVariable("clientid") Long clientid,
                              @PathVariable("requestid") Long requestid, Model model){
        Request request = requestService.findById(requestid);
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
                          @RequestParam(name = "etsp") String etsp, Principal principal){
        Comment comment = Comment.builder()
                .text("изменил ецп на " + etsp)
                .user(principal.getName())
                .build();
        request.getComments().add(comment);
        request.setEtsp(Etsp.valueOf(etsp));
        requestService.saveRequest(request);
        return "redirect:/request/{requestid}";
    }

    @PostMapping("/request/{requestid}/addcomment")
    public String addComment(@PathVariable("requestid") Request request,
                             @ModelAttribute("comment") Comment comment,
                             Principal principal){
        request.getComments().add(comment);
        request.getComments().add(Comment.builder()
                .text("добавил коментарий")
                .user(principal.getName()).build());
        requestService.saveRequest(request);
        return "redirect:/request/{requestid}";
    }

    @PostMapping("/request/{requestid}/closerequest")
    public String closeRequest(@PathVariable("requestid") Request request,
                               Principal principal){
        Comment comment = Comment.builder()
                .text("закрыл заявку")
                .user(principal.getName())
                .build();
        request.getComments().add(comment);
        request.setActive(false);
        request.setDateOfEnd(new Timestamp(new java.util.Date().getTime()));
        requestService.saveRequest(request);
        return "redirect:/request/{requestid}";
    }

    @GetMapping("/request/{requestid}")
    public String requestInfo(@PathVariable("requestid") Long requestid, Model model){
        Request request = requestService.findById(requestid);
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
                                   @PathVariable("clientid") User user, Principal principal){
        Comment comment = Comment.builder()
                .text("добавил клиента " + user.getLastName())
                .user(principal.getName()).build();
        request.getComments().add(comment);
        request.setClient(user);
        requestService.saveRequest(request);
        return "redirect:/request/{requestid}";
    }

    @GetMapping("/request/{requestid}/clients/create")
    public String requestCreateClient(@PathVariable("requestid") Long requestid, Model model){
        model.addAttribute("organizations", organizationService.findAll());
        model.addAttribute("requestid", requestid);
        return "request/request-create-client";
    }

    @PostMapping("/request/{requestid}/clients/create")
    public String requestCreateClient(@PathVariable("requestid") Request request,
                                      @ModelAttribute User user, Model model, Principal principal){
        user.setStatus(Status.Новый);
        userService.saveClient(user);
        request.getComments().add(Comment.builder()
                .text("добавил клиента " + user.getLastName())
                .user(principal.getName()).build());
        request.setClient(userService.findClientBy(
                user.getLastName(), user.getName(), user.getNameOfOrganization()
                ));
        requestService.saveRequest(request);

        return "redirect:/request/{requestid}";
    }

    @GetMapping("/request/{requestid}/clients/{clientid}/products/create")
    public String requestCreateProduct(@PathVariable("requestid") Long requestid,
                                       @PathVariable("clientid") Long clientid, Model model){
        model.addAttribute("descriptions", descriptionService.findAll());
        model.addAttribute("requestid", requestid);
        model.addAttribute("clientid", clientid);
        model.addAttribute("conditions", Condit.values());
        return "request/request-create-product";
    }

    @PostMapping("/request/{requestid}/clients/{clientid}/products/create")
    public String requestCreateProduct(@PathVariable("requestid") Request request, Model model,
                                       @PathVariable("clientid") User user,
                                       @ModelAttribute Product product, Principal principal){
        product.setUser(user);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("создал кассу").build());

        request.getComments().add(Comment.builder()
                .text("добавил кассу " + product.getName())
                .user(principal.getName()).build());
        request.setClient(user);
        request.setProduct(productService.SvaAndFlush(product));
        requestService.saveRequest(request);
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
                              @PathVariable("requestid") Request request, Principal principal){
        Comment comment = Comment.builder()
                .text("изменил тему на " + topic)
                .user(principal.getName())
                .build();
        request.getComments().add(comment);
        request.setTopic(topic);
        requestService.saveRequest(request);
        return "redirect:/request/{requestid}";

    }

//    @GetMapping("/request/{requestid}/productEquipment")
//    public String addEquipment(@PathVariable("requestid") Request request, Model model){
//        model.addAttribute("request", request);
//        return "request/addProductEquipment";
//    }
//
//    @PostMapping("/request/{requestid}/productEquipment")
//    public String addEquipment(@RequestParam("productEquipment") String productEquipment,
//                               @PathVariable("requestid") Request request, Principal principal){
//        request.setProductEquipment(productEquipment);
//        request.getComments().add(Comment.builder().user(principal.getName())
//                .text("добавил оборудование").build());
//        requestService.saveRequest(request);
//        return "redirect:/request/{requestid}";
//    }

    @GetMapping("/request/{requestid}/productCondition")
    public String addCondition(@PathVariable("requestid") Request request, Model model){
        model.addAttribute("request", request);
        return "request/addProductCondition";
    }

    @PostMapping("/request/{requestid}/productCondition")
    public String addCondition(@RequestParam("productCondition") String productCondition,
                               @PathVariable("requestid") Request request, Principal principal){
        request.setProductCondition(productCondition);
        request.getComments().add(Comment.builder().user(principal.getName())
                .text("добавил состояние оборудования").build());
        requestService.saveRequest(request);
        return "redirect:/request/{requestid}";
    }

    @GetMapping("/request/{requestid}/productDescription")
    public String addDescription(@PathVariable("requestid") Request request, Model model){
        model.addAttribute("request", request);
        return "request/addProductDescription";
    }

    @PostMapping("/request/{requestid}/productDescription")
    public String addDescription(@RequestParam("productDescription") String productDescription,
                               @PathVariable("requestid") Request request, Principal principal){
        request.setProductDescription(productDescription);
        request.getComments().add(Comment.builder().user(principal.getName())
                .text("добавил описание неисправности").build());
        requestService.saveRequest(request);
        return "redirect:/request/{requestid}";
    }

    @GetMapping("/remember/{requestid}")
    public String remember(@PathVariable("requestid") Request request, Model model,
                            Principal principal){
        model.addAttribute("request", request);
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        return "request/remember";
    }

    @GetMapping("/request/{requestid}/clients/{clientid}/products/addProduct")
    public String addProduct(@PathVariable("clientid") User user, Model model,
                             @PathVariable("requestid") Long requestid){
        model.addAttribute("requestid", requestid);
        model.addAttribute("products", productService.findByUser(user));
        return "request/add-product";
    }

    @GetMapping("/request/{requestid}/clients/{clientid}/products/{productid}")
    public String addProduct(@PathVariable("requestid") Request request, Principal principal,
                             @PathVariable("productid") Product product){
        request.setProduct(product);
        request.getComments().add(Comment.builder().user(principal.getName())
                .text("добавил кассу").build());
        requestService.saveRequest(request);
        return "redirect:/request/{requestid}";
    }

    @GetMapping("/act/{requestid}")
    public String act(@PathVariable("requestid") Request request, Model model){
        model.addAttribute("request", request);
        return "request/act";
    }


}

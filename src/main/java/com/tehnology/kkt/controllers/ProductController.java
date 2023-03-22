package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.*;
import com.tehnology.kkt.models.enums.Condit;
import com.tehnology.kkt.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final DescriptionService descriptionService;
    private final ProductService productService;
    private final UserService userService;
    private final RequestService requestService;
    private final OFDService ofdService;
    private final FNService fnService;
    private final MaintenanceService maintenanceService;
    private final TaskService taskService;

    @GetMapping("/clients/{clientid}/product")
    public String crateProduct(@PathVariable Long clientid, Model model) {
        model.addAttribute("id", clientid);
        model.addAttribute("product", new Product());
        model.addAttribute("descriptions", descriptionService.findAll());
        model.addAttribute("conditions", Condit.values());
        return "product/create-product";
    }

    @PostMapping("/clients/{clientid}/product")
    public String crateProduct(@PathVariable("clientid") User user,
                               @ModelAttribute Product product,
                               Principal principal) {
        product.getComments().add(Comment.builder()
                .text("создал кассу").user(principal.getName()).build());
        product.setUser(user);
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/delete")
    public String deleteProduct(@PathVariable Long clientid, @PathVariable Long productid){
            productService.deleteProduct(productService.findById(productid));
            return "redirect:/clients/{clientid}";
    }

    @GetMapping("/clients/{clientid}/product/{productid}")
    public String productInfo(@PathVariable Long productid, Model model){
            model.addAttribute("product", productService.findById(productid));
            return "product/product-info";
    }

    @GetMapping("/clients/{clientid}/product/{productid}/edit")
    public String editProduct(@PathVariable Long clientid,
                              @PathVariable Long productid, Model model){
        model.addAttribute("product", productService.findById(productid));
        model.addAttribute("descriptions", descriptionService.findAll());
        model.addAttribute("clientid", clientid);
        model.addAttribute("productid", productid);
        model.addAttribute("conditions", Condit.values());
        return "product/edit-product";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/edit")
    public String editProduct(@RequestParam("name") String name, @RequestParam("address") String address,
                              @RequestParam("number") String number, Principal principal,
                              @PathVariable("productid") Product product, Model model,
                              @RequestParam("condit") Condit condit){
        product.setCondit(condit);
        product.getComments().add(Comment.builder()
                .user(principal.getName())
                .text("отредактировал описание товара").build());
        product.setName(name);
        product.setAddress(address);
        product.setNumber(number);
        productService.saveProduct(product);

        return "redirect:/clients/{clientid}/product/{productid}";
    }

    @GetMapping("/clients/{clientid}/product/{productid}/changeclient")
    public String changeClient(@PathVariable Long productid, Model model){
        model.addAttribute("clients", userService.findAllClients());
        model.addAttribute("product", productService.findById(productid));
        return "change-client";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/changeclient")
    public String changeClient(@PathVariable Long productid, Product productFrom,
                               Principal principal){

        Product product = productService.findById(productid);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("изменил клиента").build());
        product.setUser(userService.findById(productFrom.getUser().getId()));
        productService.saveProduct(product);
        return "redirect:/clients/" + productFrom.getUser().getId() + "/product/{productid}/";

    }

    @GetMapping("/clients/{clientid}/request/{requestid}/kassa")
    public String addKassa(@PathVariable("clientid") Long clientid,
                           @PathVariable("requestid") Long requestid, Model model){
        User user = userService.findById(clientid);
        model.addAttribute("requestid,", requestid);
        model.addAttribute("clientid", clientid);
        model.addAttribute("kassas", user.getProducts());
        return "choose-kassa";
    }

    @PostMapping("/clients/{clientid}/request/{requestid}/kassa")
    public String addKassa(@RequestParam("id") Product product, Principal principal,
                           @PathVariable("requestid") Request request ){
        request.setProduct(product);
        requestService.saveRequest(request);
        return "redirect:/clients/{clientid}/request/{requestid}";
    }

    @GetMapping("/clients/{clientid}/request/{requestid}/kassa/{kassaid}")
    public String changeKassa(@PathVariable("requestid") Request request,
                              @PathVariable("clientid") Long clientid, Model model){
        User user = userService.findById(clientid);
        model.addAttribute("clientid", clientid);
        model.addAttribute("request", request);
        model.addAttribute("kassas", user.getProducts());
        return "change-kassa";
    }


    @GetMapping("/controls")
    public String ofdList(Model model){
        model.addAttribute("ofds", ofdService.findAllByOrderByDayEndDesc());
        model.addAttribute("fns", fnService.findAllByOrderByDayEndDesc());
        model.addAttribute("maintenances", maintenanceService.findAllByOrderByDayEndDesc());
        return "list-controls";
    }

    @GetMapping("/clients/{clientid}/product/{productid}/addComment")
    public String addComment(@RequestParam("text") String text, Principal principal,
                             @PathVariable("productid") Product product){
        product.getComments().add(Comment.builder().user(principal.getName())
                .text(text).build());
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productid}";

    }

    @GetMapping("/clients/{clientid}/product/{productid}/addTask")
    public String addTask(@PathVariable("clientid") Long clientid, Model model,
                          @PathVariable("productid") Long productid){
        model.addAttribute("clientid", clientid);
        model.addAttribute("productid", productid);
        model.addAttribute("tasks", taskService.findByTasksIsNull());
        return "product/add-task";
    }

    @GetMapping("/clients/{clientid}/product/{productid}/task/{taskid}")
    public String addTask(@PathVariable("clientid") Long clientid, Principal principal,
                          @PathVariable("productid") Product product, @PathVariable("taskid") Task task){
        product.getTasks().add(task);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("добавил задачу").build());
        task.setProduct(product);
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productid}";
    }



}

package com.tehnology.kkt.controllers;


import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.catalog.Operator;
import com.tehnology.kkt.services.OperatorService;
import com.tehnology.kkt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OperatorController {

    private final OperatorService operatorService;
    private final UserService userService;

    @GetMapping("/operator")
    public String operator(Model model) {

        model.addAttribute("operators", operatorService.findAll());

        return "operator";
    }

    @PostMapping("/operator")
    public String operator(Operator operator) {

        operatorService.save(operator);
        return "redirect:/operator";
    }

    @GetMapping("/operator/create")
    public String operatorCreator(Model model) {

        model.addAttribute("operator", new Operator());

        return "create-operator";
    }

    @GetMapping("/operator/create/{clientid}/product/{productid}/ofd")
    public String createOperatorFromOFD(@PathVariable Long clientid,
                                        @PathVariable Long productid,
                                        Model model) {

        model.addAttribute("operator", new Operator());
        model.addAttribute("clientid",clientid);
        model.addAttribute("productid", productid);

        return "create-operator";
    }

    @PostMapping("/operator/create/{clientid}/product/{productid}/ofd")
    public String returnToOFDCreate(@PathVariable Long clientid,
                                    @PathVariable Long productid,
                                    Model model, Operator operator) {
        operatorService.save(operator);
        User user = userService.findById(clientid);
        model.addAttribute("clientid",clientid);
        model.addAttribute("user",user);

        return "redirect:/clients/{clientid}/product/{productid}/ofd";
    }

    @GetMapping("/operator/{operatorid}/delete")
    public String deleteOperator(@PathVariable("operatorid") Operator operator){
        operatorService.delete(operator);
        return "redirect:/operator";
    }

}

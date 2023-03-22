package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.ContactPerson;
import com.tehnology.kkt.models.Request;
import com.tehnology.kkt.services.ContactPersonService;
import com.tehnology.kkt.services.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ContactPeopleController {

    private final ContactPersonService contactPersonService;
    private final RequestService requestService;


    @GetMapping("/request/{requestid}/contactPeople/{manid}")
    public String contactInfo(@PathVariable(name = "manid") ContactPerson contactPerson,
                              @PathVariable("requestid") Long requestid, Model model){
        model.addAttribute("person", contactPerson);
        model.addAttribute("requestid", requestid);
        return "contactperson/contactPerson";
    }

    @PostMapping("/request/{requestid}/contactPeople/{manid}")
    public String contactInfo(@PathVariable("requestid") Request request,
                              @ModelAttribute ContactPerson contactPerson,
                              Principal principal){
        request.getComments().add(Comment.builder().user(principal.getName())
                .text("изменил контакт").build());
        contactPersonService.save(contactPerson);
        return "redirect:/request/{requestid}";
    }

    @GetMapping("/request/{requestid}/contactPeople")
    public String addContact(@PathVariable("requestid") Long requestid, Model model){
        model.addAttribute("requestid", requestid);
        return "contactperson/add-person";
    }

    @PostMapping("/request/{requestid}/contactPeople")
    public String addContact(@PathVariable("requestid") Request request, Principal principal,
                             @ModelAttribute ContactPerson contactPerson){
        contactPersonService.save(contactPerson);
        request.getContactPeoples().add(
                contactPersonService.findBy(contactPerson.getNameOfContact(), contactPerson.getPhoneOfContact()));
        request.getComments().add(Comment.builder().user(principal.getName())
                .text("добавил контакт").build());
        requestService.saveRequest(request);
        return "redirect:/request/{requestid}";

    }

    @PostMapping("/request/{requestid}/contactPeople/{manid}/delete")
    public String deleteContact( @PathVariable("requestid") Request request,
                                 @PathVariable("manid") ContactPerson contactPerson){
        request.getContactPeoples().remove(contactPerson);
        contactPersonService.delete(contactPerson);
        return "redirect:/request/{requestid}";
    }

}

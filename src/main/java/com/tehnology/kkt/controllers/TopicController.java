package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Request;
import com.tehnology.kkt.models.catalog.Topic;
import com.tehnology.kkt.services.RequestService;
import com.tehnology.kkt.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;
    private final RequestService requestService;

    @GetMapping("/topics")
    public String listTopics(Model model){
        model.addAttribute("topics", topicService.findAll());
        return "topics";
    }

    @GetMapping("/topics/create")
    public String createTopic(Model model){
        model.addAttribute("topic", new Topic());
        return "create-topic";
    }

    @PostMapping("/topics/create")
    public String createTopic(Topic topic){
        topicService.saveTopic(topic);
        return"redirect:/topics";
    }

    @GetMapping("/clients/{clientid}/request/{requestid}/topic/{topicid}")
    public String changeTopic(@PathVariable Long clientid,
                              @PathVariable Long requestid,
                              @PathVariable("topicid") Topic topic, Model model){

        model.addAttribute("clientid", clientid);
        model.addAttribute("requestid", requestid);
        model.addAttribute("top", topic);
        model.addAttribute("topics", topicService.findAll());
        return "change-topic";
    }

    @PostMapping("/clients/{clientid}/request/{requestid}/topic/{topicid}")
    public String changeTopic(@RequestParam("id") Topic topic,
                              @PathVariable("requestid") Request request){
        request.setTopic(topic);
        requestService.saveRequest(request);
        return "redirect:/clients/{clientid}/request/{requestid}";

    }
}

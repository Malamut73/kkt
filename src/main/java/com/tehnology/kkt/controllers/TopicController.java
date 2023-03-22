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

    @GetMapping("/topics/{topicid}/delete")
    public String deleteTopic(@PathVariable("topicid") Topic topic){
        topicService.delete(topic);
        return "redirect:/topics";
    }


}

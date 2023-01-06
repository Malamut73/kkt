package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.extraclasses.firdirectory.Topic;
import com.tehnology.kkt.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

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
}

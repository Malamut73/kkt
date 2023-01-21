package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.Task;
import com.tehnology.kkt.services.TaskService;
import com.tehnology.kkt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.sql.Date;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @GetMapping("/tasks")
    public String tasks(Model model, Principal principal){
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("tasks", taskService.findTaskByUser(user));
        return "tasks";
    }

    @GetMapping("/tasks/create")
    public String createTask(){
        return "create-task";
    }

    @PostMapping("/tasks/create")
    public String createTask(@ModelAttribute Task task, Principal principal){
        User user = userService.findByEmail(principal.getName());
        task.setUser(user);
        task.getComments().add(Comment.builder()
                        .text(task.getComment())
                        .build());
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{taskid}")
    public String taskInfo(@PathVariable("taskid") Task task, Model model){
        model.addAttribute("task", task);
        return "info-task";
    }

    @PostMapping("/tasks/{taskid}/addtask")
    public String addTask(@ModelAttribute Comment comment,
                          @PathVariable("taskid") Task task){
        task.getComments().add(comment);
        taskService.save(task);
        return "redirect:/tasks/{taskid}/";
    }

    @PostMapping("/tasks/{taskid}/closetask")
    public String closeTask(@PathVariable("taskid") Task task){
        task.setActive(false);
        task.setDateOfEnd(new Date(new java.util.Date().getTime()));
        taskService.save(task);
        return "redirect:/tasks/";

    }

}

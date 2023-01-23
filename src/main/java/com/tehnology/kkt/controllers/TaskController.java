package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.Task;
import com.tehnology.kkt.services.TaskService;
import com.tehnology.kkt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("tasks", taskService.findAllByOrderByDateOfReminderDesc());
        return "tasks";
    }

    @GetMapping("/tasks/create")
    public String createTask(){
        return "create-task";
    }

    @PostMapping("/tasks/create")
    public String createTask(@ModelAttribute Task task){
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

    @GetMapping("/tasks/{taskid}/adduser")
    public String addUser(@PathVariable("taskid") Long taskid, Model model){
        model.addAttribute("clients", userService.findAllClients());
        model.addAttribute("taskid", taskid);
        return "choose-client";
    }

    @PostMapping("/tasks/{taskid}/adduser")
    public String addUser(@RequestParam("id") User user,
                          @PathVariable("taskid") Task task){
        task.setUser(user);
        taskService.save(task);
        return "redirect:/tasks/{taskid}";
    }

    @GetMapping("/tasks/{taskid}/user/{userid}")
    public String changeUser(@PathVariable("userid") User user,
                             @PathVariable("taskid") Long taskid, Model model){
        model.addAttribute("user", user);
        model.addAttribute("taskid", taskid);
        model.addAttribute("clients", userService.findAllClients());
        return "change-user";
    }

    @GetMapping("/tasks/{taskid}/user/{userid}/product")
    public String addProduct(@PathVariable("taskid") Task task, Model model){
        model.addAttribute("task", task);
        model.addAttribute("products", task.getUser().getProducts());
        return "choose-products-task";
    }

    @PostMapping("/tasks/{taskid}/user/{userid}/product")
    public String addProduct(@RequestParam("id")Product product,
                             @PathVariable("taskid") Task task){
        task.setProduct(product);
        taskService.save(task);
        return "redirect:/tasks/{taskid}";
    }

    @GetMapping("/tasks/{taskid}/user/{userid}/product/{productid}")
    public String changeProduct(@PathVariable("taskid") Task task, Model model){
        model.addAttribute("task", task);
        return "change-product-task";
    }

    @GetMapping("/task/{taskid}/remind")
    public String changeDateOfReminder(@PathVariable("taskid") Task task,
                                       Model model){
        model.addAttribute("task", task);
        return "change-remind";
    }

    @PostMapping("/task/{taskid}/remind")
    public String changeReminder(@RequestParam("dateOfReminder") Date dateOfReminder,
                                 @PathVariable("taskid") Task task ){
        task.setDateOfReminder(dateOfReminder);
        taskService.save(task);
        return "redirect:/tasks/{taskid}";
    }

}

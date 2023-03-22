package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.Task;
import com.tehnology.kkt.services.ProductService;
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
    private final ProductService productService;

    @GetMapping("/task/{value}")
    public String tasks(@PathVariable(name = "value", required = false) String value,
            Model model){
        model.addAttribute("tasks", taskService.findAllByActiveOrderByDateOfReminderDesc(value));
        return "task/tasks";
    }

    @GetMapping("/tasks/create")
    public String createTask(){
        return "task/create-task";
    }

    @PostMapping("/tasks/create")
    public String createTask(@ModelAttribute Task task, Principal principal){
        task.getComments().add(Comment.builder().user(principal.getName())
                        .text("создал задачу")
                        .build());
        task.setActive(true);
        taskService.save(task);
        return "redirect:/task/true";
    }

    @GetMapping("/tasks/{taskid}")
    public String taskInfo(@PathVariable("taskid") Task task, Model model){
        model.addAttribute("task", task);
        return "task/info-task";
    }

    @PostMapping("/tasks/{taskid}/addtask")
    public String addComment(@RequestParam("text") String text, Principal principal,
                             @PathVariable("taskid") Task task){
        task.getComments().add(Comment.builder().user(principal.getName())
                .text(text).build());
        taskService.save(task);
        return "redirect:/tasks/{taskid}/";
    }

    @PostMapping("/tasks/{taskid}/closetask")
    public String closeTask(@PathVariable("taskid") Task task, Principal principal){
        task.setActive(false);
        task.setDateOfEnd(new Date(new java.util.Date().getTime()));
        task.getComments().add(Comment.builder().user(principal.getName())
                .text("закрыл задачу").build());
        taskService.save(task);
        return "redirect:/tasks/";

    }

//    @GetMapping("/tasks/{taskid}/adduser")
//    public String addUser(@PathVariable("taskid") Long taskid, Model model){
//        model.addAttribute("clients", userService.findAllClients());
//        model.addAttribute("taskid", taskid);
//        return "task/choose-client";
//    }
//
//    @PostMapping("/tasks/{taskid}/adduser")
//    public String addUser(@RequestParam("id") User user,
//                          @PathVariable("taskid") Task task){
//        task.setUser(user);
//        taskService.save(task);
//        return "redirect:/tasks/{taskid}";
//    }

    @GetMapping("/tasks/{taskid}/user/{userid}")
    public String changeUser(@PathVariable("userid") User user,
                             @PathVariable("taskid") Long taskid, Model model){
        model.addAttribute("user", user);
        model.addAttribute("taskid", taskid);
        model.addAttribute("clients", userService.findAllClients());
        return "task/change-user";
    }


    @GetMapping("/task/{taskid}/remind")
    public String changeDateOfReminder(@PathVariable("taskid") Task task,
                                       Model model){
        model.addAttribute("task", task);
        return "task/change-remind";
    }

    @PostMapping("/task/{taskid}/remind")
    public String changeReminder(@RequestParam("dateOfReminder") Date dateOfReminder,
                                 @PathVariable("taskid") Task task, Principal principal ){
        task.setDateOfReminder(dateOfReminder);
        task.getComments().add(Comment.builder().user(principal.getName())
                .text("изменил время напоминания").build());
        taskService.save(task);
        return "redirect:/tasks/{taskid}";
    }

    @GetMapping("/clients/{clientid}/product/{productid}/task")
    public String createTaskFromProduct(@PathVariable("clientid") Long clientid, Model model,
                                        @PathVariable("productid") Long productid){
        model.addAttribute("clientid", clientid);
        model.addAttribute("productid", productid);
        return "task/create-task-product";
    }

    @PostMapping("/clients/{clientid}/product/{productid}/task")
    public String createTaskFromProduct(@PathVariable("productid") Product product,
                                        Task task, Principal principal){
        task.setActive(true);
        task.setProduct(product);
        task.getComments().add(Comment.builder().user(principal.getName())
                .text("создал задачу").build());
        Task newTask = taskService.saveRedirect(task);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("добавил задачу").build());
        product.getTasks().add(newTask);
        productService.saveProduct(product);
        return "redirect:/clients/{clientid}/product/{productid}";
    }

    @GetMapping("/clients/{clientid}/product/{productid}/task/{taskid}/delete")
    public String deleteTaskFormProduct(@PathVariable("productid") Product product,
                             @PathVariable("taskid") Task task, Principal principal){
        product.getTasks().remove(task);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("удалил задачу").build());
        task.setProduct(null);
        task.getComments().add(Comment.builder().user(principal.getName())
                .text("удалил кассу").build());
        taskService.save(task);
        return "redirect:/clients/{clientid}/product/{productid}";
    }

    @GetMapping("/tasks/{taskid}/product")
    public String addProductToTask(Model model, @PathVariable("taskid") Long taskid){
        model.addAttribute("taskid", taskid);
        model.addAttribute("products", productService.findAll());
        return "task/add-product";
    }

    @GetMapping("/tasks/{taskid}/product/{productid}")
    public String addProductToTask(@PathVariable("taskid") Task task, Principal principal,
                                   @PathVariable("productid") Product product){
        product.getTasks().add(task);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("добавил задачу").build());
        task.setProduct(product);
        task.getComments().add(Comment.builder().user(principal.getName())
                .text("добавил кассу").build());
        taskService.save(task);
        return "redirect:/tasks/{taskid}";

    }

    @PostMapping("/tasks/{taskid}/product/{productid}/delete")
    public String deleteProductFormTask(@PathVariable("productid") Product product,
                                        @PathVariable("taskid") Task task, Principal principal){
        product.getTasks().remove(task);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("удалил задачу").build());
        task.setProduct(null);
        task.getComments().add(Comment.builder().user(principal.getName())
                .text("удалил кассу").build());
        taskService.save(task);
        return "redirect:/tasks/{taskid}";
    }



}

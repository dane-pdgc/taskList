package com.example.tasklist.controller;

import com.example.tasklist.model.User;
import com.example.tasklist.model.Task;
import com.example.tasklist.service.TaskService;
import com.example.tasklist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class AppController{

    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "World");
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        // Assuming the username is the user's identifier
//        String username = principal.getName();
//        User user = userService.findByUsername(username);
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "dashboard"; // name of your dashboard template
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("name", "World");
        return "registration";
    }

}
package com.example.tasklist.controller;

import com.example.tasklist.model.Task;
import com.example.tasklist.model.User;
import com.example.tasklist.service.TaskService;
import com.example.tasklist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService; // Add this line


    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        // Assuming the username is the user's identifier
        String username = principal.getName();
        User user = userService.findByUsername(username);
        List<Task> tasks = taskService.getTasksByUserId(user.getId());
        model.addAttribute("tasks", tasks);
        return "dashboard"; // name of your dashboard template
    }


    @GetMapping
    public String showTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "taskForm"; // Name of the Thymeleaf template for creating tasks
    }

    @PostMapping
    public String createTask(@ModelAttribute("task") Task task, Model model) {
        taskService.createTask(task, 1L);
        model.addAttribute("tasks", taskService.getAllTasks());
        return "redirect:/tasks"; // Redirect after task creation
    }
}

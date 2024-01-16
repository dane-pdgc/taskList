package com.example.tasklist.controller;

import com.example.tasklist.model.User;
import com.example.tasklist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Add an empty user object to model to hold form data
        model.addAttribute("user", new User());
        return "register"; // Name of the Thymeleaf template for registration
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {

        boolean isRegistered = userService.registerUser(user);
        if (isRegistered) {
            return "redirect:/"; // Redirect to login page after successful registration
        } else {
            model.addAttribute("registrationError", "User registration failed");
            return "register"; // Stay on the registration page and display error
        }
    }

    @GetMapping("/loginForm")
    public String showLoginForm() {
        return "index"; // Name of the Thymeleaf template for login
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            RedirectAttributes redirectAttributes) {
        boolean isLoggedIn = userService.loginUser(username, password);
        if (isLoggedIn) {
            return "redirect:/dashboard"; // Redirect to user dashboard on successful login
        } else {
            redirectAttributes.addFlashAttribute("loginError", "Invalid username or password");
            return "redirect:/"; // Redirect to the root (index page) and carry the error message
        }
    }


}

package com.example.tasklist.graphql;

import com.example.tasklist.model.User;
import com.example.tasklist.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserDataFetcher {

    private final UserService userService;

    public UserDataFetcher(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public List<User> findAllUsers() {
        return userService.getAllUsers();
    }

    @MutationMapping
    public Boolean createUser(@Argument String username, @Argument String email, @Argument String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmailAddress(email);
        // Handle password setting, potentially with bcrypt or something later
        return userService.registerUser(user);
    }
}

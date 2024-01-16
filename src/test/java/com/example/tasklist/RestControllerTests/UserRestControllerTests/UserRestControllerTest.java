package com.example.tasklist.RestControllerTests.UserRestControllerTests;

import com.example.tasklist.controller.restful.UserRestController;
import com.example.tasklist.model.User;
import com.example.tasklist.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRestControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserRestController userRestController;

    @BeforeEach
    void setUp() {
        // This setup will be executed before each test.
    }

    @Test
    void testGetUsers() {
        // Given
        User user1 = new User(); // Set properties as needed
        User user2 = new User();
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        // When
        List<User> users = userRestController.getUsers();

        // Then
        assertEquals(2, users.size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testRegisterUserSuccess() {
        // Given
        User newUser = new User(); // Set properties
        when(userService.registerUser(any(User.class))).thenReturn(true);

        // When
        ResponseEntity<String> response = userRestController.registerUser(newUser);

        // Then
        assertEquals("Account created successfully!", response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testRegisterUserFailure() {
        // Given
        User newUser = new User(); // Set properties
        when(userService.registerUser(any(User.class))).thenReturn(false);

        // When
        ResponseEntity<String> response = userRestController.registerUser(newUser);

        // Then
        assertEquals("Sorry, there was an error in creating your account", response.getBody());
        assertEquals(400, response.getStatusCodeValue());
    }
}

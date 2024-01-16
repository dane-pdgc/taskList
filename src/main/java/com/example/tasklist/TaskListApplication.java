package com.example.tasklist;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TaskListApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskListApplication.class, args);
    }
}

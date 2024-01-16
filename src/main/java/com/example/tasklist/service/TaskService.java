package com.example.tasklist.service;

import com.example.tasklist.model.Task;
import com.example.tasklist.model.User;
import com.example.tasklist.repository.TaskRepository;
import com.example.tasklist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository; // Add this line

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository; // Add this line
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setAccount(user);
        return taskRepository.save(task);
    }

    // Method to retrieve all tasks for a specific user
    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByAccount_Id(userId);
    }
}

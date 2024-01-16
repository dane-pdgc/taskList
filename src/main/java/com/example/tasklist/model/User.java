package com.example.tasklist.model;

import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.tasklist.model.Task;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users", schema = "todo")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false)
    private String emailAddress;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "account")
    private List<Task> tasks;


    public void printAll(){
        System.out.println("Username: " + this.username);
        System.out.println("Email: " + this.emailAddress);
        System.out.println("Password: " + this.password);
    }
    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    //    public void setPassword(String password) {
//        this.password = new BCryptPasswordEncoder().encode(password);
//    }


}

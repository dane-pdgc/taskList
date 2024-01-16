package com.example.tasklist.model;

import jakarta.persistence.*;

@Entity
@Table(name="tasks", schema = "todo")
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "task_desc", nullable = false)
    private String taskDesc;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private User account; // This will create a column 'account_id' in 'tasks' table

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public User getAccount() {
        return account;
    }

    public void setAccount(User account) {
        this.account = account;
    }
}

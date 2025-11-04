package com.example.task.tasks;

public class Task {
    private static long counter = 0;
    private final long id;
    private String description;
    private TaskStatus status;

    public Task(String description) {
        this.id = ++counter;
        this.description = description;
        this.status = TaskStatus.EN_COURS;
    }

    public long getId() { return id; }

    public String getDescription() { return description; }

    public TaskStatus getStatus() { return status; }

    public void setDescription(String description) { this.description = description; }

    public void setStatus(TaskStatus status) { this.status = status; }

}

package com.example.task.service;

import com.example.task.tasks.Task;
import com.example.task.tasks.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class TaskService {
    private Map<Long, Task> tasks = new HashMap<>();

    public TaskService() {
        addTask("Faire les courses");
        addTask("Ranger les courses");
    }

    public Task addTask (String description) {
        Task task = new Task(description);
        tasks.put(task.getId(), task);
        return task;
    }

    public void deleteTask (Long id) { tasks.remove(id); }

    public Task getTask (Long id) { return tasks.get(id); }

    public Collection<Task> getAllTasks() { return tasks.values(); }

    public void finishTask(Long id) {
        Task task = getTask(id);

        if (task == null) {
            throw new IllegalArgumentException("Tâche " + id + " introuvable.");
        }

        task.setStatus(TaskStatus.TERMINE);
    }
}
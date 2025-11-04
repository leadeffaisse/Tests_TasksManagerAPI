package com.example.task.tasks;

import org.junit.jupiter.api.Test;

import static com.example.task.tasks.TaskStatus.EN_COURS;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testIdIncrementation() {
        Task task1 = new Task("Tâche 1");
        Task task2 = new Task("Tâche 2");
        Task task3 = new Task("Tâche 3");

        assertEquals(task1.getId() + 1, task2.getId());
        assertEquals(task2.getId() + 1, task3.getId());
    }

    @Test
    public void testDefaultStatus() {
        Task task = new Task("Faire les courses");

        assertEquals(EN_COURS, task.getStatus());
    }
}
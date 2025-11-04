package com.example.task.service;
import com.example.task.tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.task.tasks.TaskStatus.*;
import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    @Test
    public void testAddTask() {
        //Arrange
        TaskService service = new TaskService();

        //Act
        service.addTask("Faire les courses");

        //Assert
        Collection<Task> tasks = service.getAllTasks();
        assertEquals(1, tasks.size());
        List<Task> taskList = new ArrayList<>(tasks);
        assertEquals("Faire les courses", taskList.getFirst().getDescription());
        assertEquals(EN_COURS, taskList.getFirst().getStatus());
    }

    @Test
    public void testDeleteTask() {
        TaskService service = new TaskService();
        Task task = service.addTask("Faire les courses");

        service.deleteTask(task.getId());

        Collection<Task> tasks = service.getAllTasks();
        assertEquals(0, tasks.size());
        assertNull(service.getTask(task.getId()));
    }

    @Test
    public void testGetTask() {
        TaskService service = new TaskService();
        Task task1 = service.addTask("Faire les courses");
        Task task2 = service.addTask("Ranger les courses");

        task1 = service.getTask(task1.getId());

        assertEquals("Faire les courses", task1.getDescription());
        assertEquals(EN_COURS, task1.getStatus());
        assertEquals(task1.getId() + 1, task2.getId());
    }

    @Test
    public void testGetAllTasks() {
        TaskService service = new TaskService();
        service.addTask("Faire les courses");
        service.addTask("Ranger les courses");

        Collection<Task> tasks = service.getAllTasks();
        assertEquals(2, tasks.size());
    }

    @Test
    public void testFinishTask() {
        TaskService service = new TaskService();
        Task task = service.addTask("Faire les courses");

        service.finishTask(task.getId());

        assertEquals(TERMINE, task.getStatus());
    }
}
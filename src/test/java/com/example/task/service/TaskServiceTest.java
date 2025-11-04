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
        assertEquals("Faire les courses", taskList.get(0).getDescription());
        assertEquals(EN_COURS, taskList.get(0).getStatus());
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
        Task task = service.addTask("Faire les courses");

        task = service.getTask(task.getId());

        assertEquals("Faire les courses", task.getDescription());
        assertEquals(EN_COURS, task.getStatus());
        assertEquals(1, task.getId());
    }

    @Test
    public void testGetAllTasks() {
        TaskService service = new TaskService();
        Task task = service.addTask("Faire les courses");
        Task task1 = service.addTask("Ranger les courses");

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
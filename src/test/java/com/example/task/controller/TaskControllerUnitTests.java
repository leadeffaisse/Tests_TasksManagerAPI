package com.example.task.controller;

import com.example.task.service.TaskService;
import com.example.task.tasks.Task;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
public class TaskControllerUnitTests {
    @Autowired
    private MockMvc mockMvc; //Object used to make HTTP request on our API

    @MockitoBean
    private TaskService taskService; //Mock object TaskService automatically injected in TaskController instance

    @Test
    void hello_should_return_message() throws Exception {
        mockMvc.perform(get("/tasks/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to the Task Manager API!"));
    }

    @Test
    void getAllTasks_should_return_list_of_tasks() throws Exception {
        // Préparation des données
        List<Task> tasks = List.of(
                new Task("Tâche 1"),
                new Task("Tâche 2")
        );

        // Programmer le mock
        Mockito.when(taskService.getAllTasks()).thenReturn(tasks);

        //Exécuter la requête
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].description").value("Tâche 1"))
                .andExpect(jsonPath("$[1].id").isNotEmpty())
                .andExpect(jsonPath("$[1].description").value("Tâche 2"));
    }

    @Test
    void getAllTasks_should_return_empty_list() throws Exception {
        List<Task> tasks = List.of();

        Mockito.when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
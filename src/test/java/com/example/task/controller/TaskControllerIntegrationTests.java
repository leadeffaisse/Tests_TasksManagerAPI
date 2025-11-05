package com.example.task.controller;

import com.example.task.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskService taskService; // real TaskService object automatically injected in TaskController instance

    @Test
    void getAllTasks_integration_test() throws Exception {
        //Arrange
        taskService.addTask("Tâche de test 1");
        taskService.addTask("Tâche de test 2");

        //Act & Assert
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].description").value("Faire les courses"))
                .andExpect(jsonPath("$[0].status").value("EN_COURS"))
                .andExpect(jsonPath("$[1].id").isNotEmpty())
                .andExpect(jsonPath("$[1].description").value("Ranger les courses"))
                .andExpect(jsonPath("$[1].status").value("EN_COURS"));
    }
}
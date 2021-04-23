package com.deloitte.todo.controllers;

import com.deloitte.todo.repository.TodoRepository;
import com.deloitte.todo.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class TodoControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    public MockMvc mockMvc;

    @MockBean
    TodoRepository todoRepository;

    @MockBean
    TodoService todoService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/").with(SecurityMockMvcRequestPostProcessors.httpBasic("user", "pwd123")))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void todos() throws Exception {
        mockMvc.perform(get("/api/todos").with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "pwd123")))
                .andExpect(status().isOk())
                .andExpect(view().name("todos"))
                .andExpect(model().attributeExists("todos"));
    }

    @Test
    void todosUnauthorized() throws Exception {
        mockMvc.perform(get("/api/todos").with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "wrongpass")))
                .andExpect(status().isUnauthorized());
    }
}

package com.example.audit.controller;

import com.example.audit.core.dto.LogInfoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LogsControllerTest extends ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("Admin")
    void getUserLogs() throws Exception {
        List<LogInfoDto> logsList = List.of(
                new LogInfoDto("Admin", "User registered a new account of", "User",
                        "september", 17, 10, 16),
                new LogInfoDto("Admin", "User logged in application", null,
                        "september", 17, 10, 15));

        mockMvc.perform(MockMvcRequestBuilders.get("/logs")
                .param("id", "1")
                .header("Content-Type", "application/json"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attribute("logsList", logsList))
                .andExpect(MockMvcResultMatchers.view().name("logs"));
    }
}

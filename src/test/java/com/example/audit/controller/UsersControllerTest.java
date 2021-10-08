package com.example.audit.controller;

import com.example.audit.core.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UsersControllerTest extends ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("Admin")
    void getUserLogs() throws Exception {
        var userA = new UserDto(1L, "Admin");
        var userB = new UserDto(2L, "User");
        List<UserDto> userList = List.of(userA, userB);

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .header("Content-Type", "application/json"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attribute("userList", userList))
                .andExpect(MockMvcResultMatchers.view().name("list"));
    }
}

package com.insightleave.userservice.controllers;

import com.insightleave.userservice.dtos.UserGetDto;
import com.insightleave.userservice.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void shouldReturnAllUsers() throws Exception {
        UserGetDto userGetDto = UserGetDto.builder()
                .title("title")
                .email("email")
                .firstname("firstname")
                .lastname("lastname")
                .startDate("startdate")
                .endDate("enddate")
                .build();
        BDDMockito.given(userService.findAllUsers()).willReturn(List.of(userGetDto));
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].firstname").value(containsInAnyOrder("firstname")));
    }
}

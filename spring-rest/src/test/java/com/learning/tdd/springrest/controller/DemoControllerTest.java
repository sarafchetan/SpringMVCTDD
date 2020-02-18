package com.learning.tdd.springrest.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.web.*;
import org.springframework.test.web.servlet.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getWithOkStatus() throws Exception {
        mockMvc.perform(get("/sayHello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello There!"));
    }

    @Test
    void getOkStatusWithNameParameter() throws Exception {
        mockMvc.perform(get("/sayHello").param("firstName","Shailesh"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Shailesh!"));
    }

    @Test
    void getBadRequestStatus() throws Exception {
        mockMvc.perform(get("/sayHello").param("firstName","23234"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("First name needs to be alphabets"));
    }

    @Test
    void postWithCreatedStatus() throws Exception {
        mockMvc.perform(post("/sayHello").param("firstName", "Chetan"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Record created for Chetan!"));
    }

    @Test
    void postWithFirstNameAsADynamicParameter() throws Exception {
        mockMvc.perform(post("/sayHello").param("firstName", "Shailesh"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Record created for Shailesh!"));
    }

    @Test
    void postReturnsBadRequestForBlankFirstName() throws Exception {
        mockMvc.perform(post("/sayHello").param("firstName", ""))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("First name needs to be valid"));
    }

    @Test
    void postReturnsBadRequestForNonAlphabetOnlyFirstName() throws Exception {
        mockMvc.perform(post("/sayHello").param("firstName", "12341"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("First name needs to be valid"));

        mockMvc.perform(post("/sayHello").param("firstName", "12sdfgfsd"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("First name needs to be valid"));

        mockMvc.perform(post("/sayHello").param("firstName", "Asda2342"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("First name needs to be valid"));
    }

    @Test
    void postReturnsBadRequestForMissingFirstName() throws Exception {
        mockMvc.perform(post("/sayHello"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("firstName is missing"));
    }
    
    @Test
    void putReturnStatus() throws Exception {
    	mockMvc.perform(put("/sayHello"))
    			.andExpect(status().isOk());
    }
    
}

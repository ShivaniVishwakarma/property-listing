package com.engelandvolkers.property_listing.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void testLoginSuccess() throws Exception {
//        String loginRequest = "{\"email\": \"user@example.com\", \"password\": \"password\"}";
//
//        mockMvc.perform(post("/task/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(loginRequest))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Login successful"));
//    }
//
//    @Test
//    public void testLoginMissingEmail() throws Exception {
//        String loginRequest = "{\"email\": \"\", \"password\": \"password\"}";
//
//        mockMvc.perform(post("/task/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(loginRequest))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Email is required"));
//    }
//
//    @Test
//    public void testLoginMissingPassword() throws Exception {
//        String loginRequest = "{\"email\": \"user@example.com\", \"password\": \"\"}";
//
//        mockMvc.perform(post("/task/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(loginRequest))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Password is required"));
//    }
//
//    @Test
//    public void testLoginInvalidEmail() throws Exception {
//        String loginRequest = "{\"email\": \"invalid-email\", \"password\": \"password\"}";
//
//        mockMvc.perform(post("/task/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(loginRequest))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Invalid email format"));
//    }
//
//    @Test
//    public void testLoginEmailFromTestCom() throws Exception {
//        String loginRequest = "{\"email\": \"user@test.com\", \"password\": \"password\"}";
//
//        mockMvc.perform(post("/task/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(loginRequest))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("E-Mails from test.com are invalid"));
//    }
//
//    @Test
//    public void testLoginInvalidCredentials() throws Exception {
//        String loginRequest = "{\"email\": \"user@example.com\", \"password\": \"wrongpassword\"}";
//
//        mockMvc.perform(post("/task/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(loginRequest))
//                .andExpect(status().isUnauthorized())
//                .andExpect(content().string("Invalid email or password"));
//    }
}
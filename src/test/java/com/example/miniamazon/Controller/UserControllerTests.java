package com.example.miniamazon.Controller;

import com.example.miniamazon.Model.User;
import com.example.miniamazon.Repository.OrderRepository;
import com.example.miniamazon.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // avoiding foreign key constraint violation
        orderRepository.deleteAll();

        // Clear the database before each test
        userRepository.deleteAll();
    }

    @Test
    void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetUserById() throws Exception {
        User user = User.builder()
                .name("Test User")
                .email("test@gmail.com")
                .phone("1234567890")
                .address("Test Address")
                .password("password")
                .creditCardNumber("1234 5678 1234 5678")
                .orders(Collections.emptyList())
                .build();
        userRepository.save(user);

        Long id = user.getId();

        String uri = UriComponentsBuilder.fromPath("/api/users/{id}")
                .buildAndExpand(id)
                .toUriString();

        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void testAddUser() throws Exception {
        String userJson = "{\n" +
                "  \"name\": \"Mohamed Yehia\",\n" +
                "  \"email\": \"medoyehia2001@gmail.com\",\n" +
                "  \"password\": \"lol\",\n" +
                "  \"address\": \"23 lol ST\",\n" +
                "  \"phone\": \"0100200300\",\n" +
                "  \"creditCardNumber\": \"1234 5678 1234 5678\"\n" +
                "}";

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testUpdateUser() throws Exception {
        // Add a user to the database
        User user = User.builder()
                .name("Test User")
                .email("test@gmail.com")
                .phone("1234567890")
                .address("Test Address")
                .password("password")
                .creditCardNumber("1234 5678 1234 5678")
                .orders(Collections.emptyList())
                .build();
        userRepository.save(user);

        Long id = user.getId();

        String uri = UriComponentsBuilder.fromPath("/api/users/{id}")
                .buildAndExpand(id)
                .toUriString();

        String updatedUserJson = "{\n" +
                        "  \"name\": \"Test User\",\n" +
                        "  \"email\": \"test@gmail.com\",\n" +
                        "  \"phone\": \"1234567890\",\n" +
                        "  \"address\": \"Test Address\",\n" +
                        "  \"password\": \"password\",\n" +
                        "  \"creditCardNumber\": \"1234 1234 1234 1234\"\n" +
                        "}";

        mockMvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.creditCardNumber").value("1234 1234 1234 1234")); // Check if the credit card number is updated



        mockMvc.perform(put("/api/users/999") // Use an id that does not exist
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedUserJson))
                .andExpect(status().isNotFound());


    }

    @Test
    void testDeleteUser() throws Exception {
        // Add a user to the database
        User user = User.builder()
                .name("Test User")
                .email("test@gmail.com")
                .phone("1234567890")
                .address("Test Address")
                .password("password")
                .creditCardNumber("1234 5678 1234 5678")
                .orders(Collections.emptyList())
                .build();
        userRepository.save(user);

        Long id = user.getId();

        String uri = UriComponentsBuilder.fromPath("/api/users/{id}")
                .buildAndExpand(id)
                .toUriString();

        mockMvc.perform(delete(uri))
                .andExpect(status().isNoContent());

        mockMvc.perform(delete("/api/products/999"))
                .andExpect(status().isNotFound());
    }
}
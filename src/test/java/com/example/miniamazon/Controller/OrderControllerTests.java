package com.example.miniamazon.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testGetAllOrders() throws Exception {
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetOrderById() throws Exception {
        Long id = 23L; // Use the id of an order that exists in the database

        String uri = UriComponentsBuilder.fromPath("/api/orders/{id}")
                .buildAndExpand(id)
                .toUriString();

        mockMvc.perform(get(uri)) // Use the id of the order you created
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void testAddOrder() throws Exception {
        String orderJson1 = "{\n" +
                "  \"status\": \"Delivered\",\n" +
                "  \"totalAmount\": 1500.00,\n" +
                "  \"user\": {\n" +
                "    \"id\": 9\n" +
                "  },\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"id\": 11\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testUpdateOrder() throws Exception { // Update status of an existing order
        Long id = 23L; // Use the id of an order that exists in the database

        String uri = UriComponentsBuilder.fromPath("/api/orders/{id}")
                .buildAndExpand(id)
                .toUriString();

        String updatedOrderJson = "{\n" +
                "  \"status\": \"Delivered\",\n" +
                "  \"totalAmount\": 1500.00,\n" +
                "  \"user\": {\n" +
                "    \"id\": 9\n" +
                "  },\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"id\": 11\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        mockMvc.perform(put(uri) // Use the id of an existing order
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedOrderJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Delivered")); // Check if the status is updated

        mockMvc.perform(put("/api/orders/999") // Use an id that does not exist
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedOrderJson))
                .andExpect(status().isNotFound());


    }

    @Test
    void testDeleteOrder() throws Exception {
        Long id = 29L; // Use the id of an order that exists in the database

        String uri = UriComponentsBuilder.fromPath("/api/orders/{id}")
                .buildAndExpand(id)
                .toUriString();


        mockMvc.perform(delete(uri)) // Use the id of the order you created
                .andExpect(status().isNoContent());

        mockMvc.perform(delete("/api/orders/999")) // Use an id that does not exist
                .andExpect(status().isNotFound());


    }
}
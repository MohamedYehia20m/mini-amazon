package com.example.miniamazon.Controller;

import com.example.miniamazon.Model.Product;
import com.example.miniamazon.Repository.ProductRepository;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        // Clear the database before each test
        productRepository.deleteAll();
    }

    @Test
    void testGetAllProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetProductById() throws Exception {
        Product product = Product.builder()
                .name("Test Product")
                .category("Test Category")
                .price(100.0)
                .quantity(10)
                .description("Test Description")
                .sellerName("Test Seller")
                .sellerBankAccount("1234 5678 1234 5678")
                .build();
        productRepository.save(product);

        Long id =  product.getId();

        String uri = UriComponentsBuilder.fromPath("/api/products/{id}")
                .buildAndExpand(id)
                .toUriString();

        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(product.getId()));
    }

    @Test
    void testAddProduct() throws Exception {
        String productJson = "{\n" +
                "\"name\": \"Test Product\",\n" +
                "\"category\": \"Test Category\",\n" +
                "\"price\": 100.0,\n" +
                "\"quantity\": 10,\n" +
                "\"description\": \"Test Description\",\n" +
                "\"sellerName\": \"Test Seller\",\n" +
                "\"sellerBankAccount\": \"1234 5678 1234 5678\"\n" +
                "}";
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testUpdateProduct() throws Exception {
        // Add a product to the database
        Product product = Product.builder()
                .name("Test Product")
                .category("Test Category")
                .price(100.0)
                .quantity(10)
                .description("Test Description")
                .sellerName("Test Seller")
                .sellerBankAccount("1234 5678 1234 5678")
                .build();
        productRepository.save(product);

        Long id = product.getId();

        String uri = UriComponentsBuilder.fromPath("/api/products/{id}")
                .buildAndExpand(id)
                .toUriString();

        String updatedProductJson = "{\n" +
                "\"name\": \"Updated Product\",\n" +
                "\"category\": \"Test Category\",\n" +
                "\"price\": 10000.0,\n" +
                "\"quantity\": 10,\n" +
                "\"description\": \"Test Description\",\n" +
                "\"sellerName\": \"Test Seller\",\n" +
                "\"sellerBankAccount\": \"1234 5678 1234 5678\"\n" +
                "}";

        mockMvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedProductJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Product")) // update the name to "Updated Product"
                .andExpect(jsonPath("$.price").value(10000.0)); // update the price to 10000.0

        mockMvc.perform(put("/api/products/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedProductJson))
                .andExpect(status().isNotFound());


    }

    @Test
    void testDeleteProduct() throws Exception {
        // Add a product to the database
        Product product = Product.builder()
                .name("Test Product")
                .category("Test Category")
                .price(100.0)
                .quantity(10)
                .description("Test Description")
                .sellerName("Test Seller")
                .sellerBankAccount("1234 5678 1234 5678")
                .build();
        productRepository.save(product);

        Long id = product.getId();

        String uri = UriComponentsBuilder.fromPath("/api/products/{id}")
                .buildAndExpand(id)
                .toUriString();

        mockMvc.perform(delete(uri))
                .andExpect(status().isNoContent());

        mockMvc.perform(delete("/api/products/999"))
                .andExpect(status().isNotFound());


    }
}
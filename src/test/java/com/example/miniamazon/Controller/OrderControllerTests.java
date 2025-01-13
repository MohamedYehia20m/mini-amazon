package com.example.miniamazon.Controller;

import com.example.miniamazon.Model.Order;
import com.example.miniamazon.Model.Product;
import com.example.miniamazon.Model.User;
import com.example.miniamazon.Repository.*;

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

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        // Clear the database before each test
        orderRepository.deleteAll();
    }

    @Test
    void testGetAllOrders() throws Exception {
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetOrderById() throws Exception {
        // add a new user
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

        // add a new product
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

        // add a new order
        Order order = Order.builder()
                .status("Delivered")
                .totalAmount(1500.00)
                .user(user)
                .products(Collections.singleton(product))
                .build();
        orderRepository.save(order);

        Long id = order.getId();

        String uri = UriComponentsBuilder.fromPath("/api/orders/{id}")
                .buildAndExpand(id)
                .toUriString();

        mockMvc.perform(get(uri)) // Use the id of the order you created
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void testAddOrder() throws Exception {
        // add a new user
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

        // add a new product
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

        Long userId = user.getId();
        Long productId = product.getId();

        String orderJson1 = "{\n" +
                "  \"status\": \"Delivered\",\n" +
                "  \"totalAmount\": 1500.00,\n" +
                "  \"user\": {\n" +
                "    \"id\": " + userId + "\n" +
                "  },\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"id\": " + productId + "\n" +
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
    void testUpdateOrder() throws Exception {
        // add a new user
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

        // add a new product
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

        // add a new order
        Order order = Order.builder()
                .status("PENDING")
                .totalAmount(1500.00)
                .user(user)
                .products(Collections.singleton(product))
                .build();
        orderRepository.save(order);

        Long id = order.getId();
        Long userId = user.getId();
        Long productId = product.getId();

        String uri = UriComponentsBuilder.fromPath("/api/orders/{id}")
                .buildAndExpand(id)
                .toUriString();

        String updatedOrderJson = "{\n" +
                "  \"status\": \"Delivered\",\n" +
                "  \"totalAmount\": 1500.00,\n" +
                "  \"user\": {\n" +
                "    \"id\": " + userId + "\n" +
                "  },\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"id\": " + productId +"\n" +
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
        // add a new user
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

        // add a new product
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

        // add a new order
        Order order = Order.builder()
                .status("Delivered")
                .totalAmount(1500.00)
                .user(user)
                .products(Collections.singleton(product))
                .build();
        orderRepository.save(order);

        Long id = order.getId();

        String uri = UriComponentsBuilder.fromPath("/api/orders/{id}")
                .buildAndExpand(id)
                .toUriString();


        mockMvc.perform(delete(uri)) // Use the id of the order you created
                .andExpect(status().isNoContent());

        mockMvc.perform(delete("/api/orders/999")) // Use an id that does not exist
                .andExpect(status().isNotFound());


    }
}
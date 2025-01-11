package com.example.miniamazon.Service;

import com.example.miniamazon.Model.Order;
import com.example.miniamazon.Repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        Optional<Order> order1 = orderRepository.findById(id);
        return order1.get();


    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public ResponseEntity<Order> updateOrder(Long id, Order orderDetails) {
        try {
            Order order = getOrderById(id);
            if (order == null) {
                return ResponseEntity.notFound().build();
            }

            order.setStatus(orderDetails.getStatus());
            order.setTotalAmount(orderDetails.getTotalAmount());
            order.setUser(orderDetails.getUser());
            order.setProducts(orderDetails.getProducts());

            return ResponseEntity.ok(orderRepository.save(order));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteOrder(Long id) {
        //return status code 204 if the order is deleted successfully
        try {
            Order order = getOrderById(id);
            orderRepository.delete(order);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
}
package com.example.miniamazon.Service;

import com.example.miniamazon.Model.Order;
import com.example.miniamazon.Model.Product;
import com.example.miniamazon.Repository.ProductRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.get();

    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public ResponseEntity<Product> updateProduct(Long id, Product productDetails) {
        try {
            Product product = getProductById(id);
            if (product == null) {
                return ResponseEntity.notFound().build();
            }

            product.setName(productDetails.getName());
            product.setCategory(productDetails.getCategory());
            product.setPrice(productDetails.getPrice());
            product.setQuantity(productDetails.getQuantity());
            product.setDescription(productDetails.getDescription());
            product.setSellerName(productDetails.getSellerName());
            product.setSellerBankAccount(productDetails.getSellerBankAccount());

            return ResponseEntity.ok(productRepository.save(product));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteProduct(Long id) {
        //return status code 204 if the order is deleted successfully
        try {
            Product product = getProductById(id);
            productRepository.delete(product);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

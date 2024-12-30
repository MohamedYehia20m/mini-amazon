package com.example.miniamazon.Service;

import com.example.miniamazon.Model.Product;
import com.example.miniamazon.Repository.ProductRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setCategory(productDetails.getCategory());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());
        product.setDescription(productDetails.getDescription());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    /*
    public List<Product> searchProducts(String query) {
        return productRepository.findByNameContaining(query);
    }


    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getProductsByPriceRange(Double min, Double max) {
        return productRepository.findByPriceBetween(min, max);
    }

    public List<Product> getProductsByStockRange(Integer min, Integer max) {
        return productRepository.findByStockBetween(min, max);
    }

    public List<Product> getProductsByCategoryAndPriceRange(String category, Double min, Double max) {
        return productRepository.findByCategoryAndPriceBetween(category, min, max);
    }

    public List<Product> getProductsByCategoryAndStockRange(String category, Integer min, Integer max) {
        return productRepository.findByCategoryAndStockBetween(category, min, max);
    }

    public List<Product> getProductsByPriceAndStockRange(Double minPrice, Double maxPrice, Integer minStock, Integer maxStock) {
        return productRepository.findByPriceBetweenAndStockBetween(minPrice, maxPrice, minStock, maxStock);
    }

    public List<Product> getProductsByCategoryAndPriceAndStockRange(String category, Double minPrice, Double maxPrice, Integer minStock, Integer maxStock) {
        return productRepository.findByCategoryAndPriceBetweenAndStockBetween(category, minPrice, maxPrice, minStock, maxStock);
    }
    */


}

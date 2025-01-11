package com.example.miniamazon.Model;


import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private double price;

    private int quantity;

    private String description;

    private String sellerName;

    private String sellerBankAccount;

    //sample product
    /*
    "name" : "Iphone 12",
    "category" : "Electronics",
    "price" : 1200,
    "quantity" : 10,
    "description" : "The latest Iphone",
    "sellerName" : "Apple",
    "sellerBankAccount" : "1234 5678 1234 5678",
     */
}

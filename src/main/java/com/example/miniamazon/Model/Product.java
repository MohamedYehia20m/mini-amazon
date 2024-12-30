package com.example.miniamazon.Model;


import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String category;

    private double price;

    private int quantity;

    private String description;

    //sample product
    /*
    "name" : "Iphone 12",
    "category" : "Electronics",
    "price" : 1200,
    "quantity" : 10,
    "description" : "The latest Iphone"
     */
}

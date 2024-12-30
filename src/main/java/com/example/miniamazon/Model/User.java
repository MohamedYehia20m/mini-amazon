package com.example.miniamazon.Model;


import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    // consider proper validation for the fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private String password;

    private String address;

    private String phone;

    private String creditCardNumber;
    /*
    "name" : "Mohamed Yehia",
    "email" : "medoyehia2001@gmail.com",
    "password" : "lol",
    "address" : "23 lol ST",
    "phone" : "01154793720",
    "creditCardNumber" : "1234 5678 1234 5678"
    */


}

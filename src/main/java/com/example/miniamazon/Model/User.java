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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;

    @NotBlank(message = "Credit card number is required")
    @Pattern(
            regexp = "\\d{16}",
            message = "Credit card number must be exactly 16 digits"
    )
    private String creditCardNumber;


}

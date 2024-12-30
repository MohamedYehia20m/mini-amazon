package com.example.miniamazon.Model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String sellerName;

    private String sellerEmail;

    private String sellerPhone;

    private String sellerAddress;

    private String sellerBankAccount;

    //sample seller
    /*
    * "sellerName" : "Ahmed",
    * "sellerEmail"  : "A@gmail.com",
    * "sellerPhone" : "01010101010",
    * "sellerAddress" : "15 Tahrir ST",
    * "sellerBankAccount" : "IE64 IRCE 9205 0112 3456 78"
    */

}

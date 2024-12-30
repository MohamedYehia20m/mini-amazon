package com.example.miniamazon.Controller;


import com.example.miniamazon.Model.Seller;
import com.example.miniamazon.Service.SellerService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers() {
        return ResponseEntity.ok(sellerService.getAllSellers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSeller(@PathVariable Long id) {
        return ResponseEntity.ok(sellerService.getSellerById(id));
    }

    @PostMapping
    public ResponseEntity<Seller> addSeller(@RequestBody Seller seller) {
        return ResponseEntity.ok(sellerService.addSeller(seller));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable Long id, @RequestBody Seller seller) {
        return ResponseEntity.ok(sellerService.updateSeller(id, seller));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }

}

package com.example.miniamazon.Service;

import com.example.miniamazon.Repository.SellerRepository;
import lombok.*;
import org.springframework.stereotype.*;
import com.example.miniamazon.Model.Seller;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller getSellerById(Long id) {
        return sellerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Seller not found"));
    }

    public Seller addSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public Seller updateSeller(Long id, Seller sellerDetails) {
        Seller seller = getSellerById(id);
        seller.setSellerName(sellerDetails.getSellerName());
        seller.setSellerEmail(sellerDetails.getSellerEmail());
        seller.setSellerAddress(sellerDetails.getSellerAddress());
        seller.setSellerPhone(sellerDetails.getSellerPhone());
        seller.setSellerBankAccount(sellerDetails.getSellerBankAccount());
        return sellerRepository.save(seller);
    }

    public void deleteSeller(Long id) {
        sellerRepository.deleteById(id);
    }

    /**/






}

package com.example.miniamazon.Service;

import com.example.miniamazon.Model.Product;
import com.example.miniamazon.Model.User;
import com.example.miniamazon.Repository.UserRepository;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    public final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<User> updateUser(Long id, User user) {
        try{
            User user1 = getUserById(id);
            if(user1 == null){
                return ResponseEntity.notFound().build();
            }

            user1.setName(user.getName());
            user1.setEmail(user.getEmail());
            user1.setAddress(user.getAddress());
            user1.setPhone(user.getPhone());
            user1.setPassword(user.getPassword());
            user1.setCreditCardNumber(user.getCreditCardNumber());

            return ResponseEntity.ok(userRepository.save(user1));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity<Void> deleteUser(Long id) {
        //return status code 204 if the order is deleted successfully
        try {
            User user = getUserById(id);
            userRepository.delete(user);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

}

package com.example.miniamazon.Service;

import com.example.miniamazon.Model.User;
import com.example.miniamazon.Repository.UserRepository;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@RequiredArgsConstructor
@Service
public class UserService {

    public final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /*
    public ResponseEntity<User> searchUsers(String query) {
        return ResponseEntity.ok(userRepository.findByUsernameContaining(query));
    }

    public ResponseEntity<User> getUsersByCategory(String category) {
        return ResponseEntity.ok(userRepository.findByCategory(category));
    }

    */

    public User updateUser(Long id, User user) {
        return userRepository.save(user);
    }

    public ResponseEntity<User> deleteUser(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }




}

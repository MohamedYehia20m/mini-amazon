package com.example.miniamazon.Service;

import com.example.miniamazon.Model.User;
import com.example.miniamazon.Repository.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User user) {
        User user2 = getUserById(id);
        user2.setEmail(user.getEmail());
        user2.setAddress(user.getAddress());
        user2.setPhone(user.getPhone());
        user2.setPassword(user.getPassword());
        user2.setName(user.getName());
        user2.setCreditCardNumber(user.getCreditCardNumber());
        return userRepository.save(user2);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }




}

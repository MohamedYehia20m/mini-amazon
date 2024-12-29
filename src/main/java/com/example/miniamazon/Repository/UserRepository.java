package com.example.miniamazon.Repository;

import com.example.miniamazon.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface UserRepository extends JpaRepository<User, Long> {
    /*
    User findByUsernameContaining(String query);
    User findByCategory(String category);
    */
}

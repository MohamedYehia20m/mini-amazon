package com.example.miniamazon.Repository;

import com.example.miniamazon.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;




public interface UserRepository extends JpaRepository<User, Long> {

}

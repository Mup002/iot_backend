package com.example.iot_demo.repository;


import com.example.iot_demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByName(String name);
    User findUserById(Long id);
}

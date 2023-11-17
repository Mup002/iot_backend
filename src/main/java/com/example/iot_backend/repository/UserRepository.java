package com.example.iot_backend.repository;

import com.example.iot_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByName(String name);
    User findUserById(Long id);
}

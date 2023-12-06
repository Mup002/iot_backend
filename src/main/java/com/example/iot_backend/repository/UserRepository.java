package com.example.iot_backend.repository;

import com.example.iot_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.name like %:name%")
    List<User> findUsersByName(@Param("name") String name);

    @Query("select u from User u where u.phone like %:phone%")
    List<User> findUsersByPhone(@Param("phone")  String phone);
    User findUserById(Long id);
    User findUserByName(String name);
    User findUserByPhone(String phone);

}

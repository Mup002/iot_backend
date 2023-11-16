package com.example.iot_demo.service;


import com.example.iot_demo.entity.Bill;
import com.example.iot_demo.entity.User;
import com.example.iot_demo.utils.request.UserRequest;
import com.example.iot_demo.utils.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<UserResponse> getAllUser();
    UserResponse addNewUser(UserRequest userRequest);
    Optional<UserResponse> getUserById(Long id);
    String updatePoint(User user, Double point);

}

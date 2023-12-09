package com.example.iot_backend.service;

import com.example.iot_backend.entity.User;
import com.example.iot_backend.utils.request.UserRequest;
import com.example.iot_backend.utils.response.UserDetailResponse;
import com.example.iot_backend.utils.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<UserResponse> getAllUser();
    UserResponse addNewUser(UserRequest userRequest);
    String updatePoint(User user, Double point);
    UserResponse getUserCurrent();
    List<UserResponse> findUsersByName(String name);
    List<UserResponse> findUsersByPhone(String phone);
    List<UserResponse> findUserByProductSold(Long id);
}

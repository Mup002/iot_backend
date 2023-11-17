package com.example.iot_backend.service.impl;


import com.example.iot_backend.entity.RFID;
import com.example.iot_backend.entity.User;
import com.example.iot_backend.repository.RFIDRepository;
import com.example.iot_backend.repository.UserRepository;
import com.example.iot_backend.service.RfidService;
import com.example.iot_backend.utils.mapper;
import com.example.iot_backend.utils.request.UserRequest;
import com.example.iot_backend.utils.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.example.iot_backend.service.UserService;
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RFIDRepository rfidRepository;
    private final RfidService rfidService;
    @Override
    public List<UserResponse> getAllUser() {
        return mapper.userToUserResponseList(StreamSupport.stream(userRepository.findAll().spliterator(),false).collect(Collectors.toList()));
    }

    @Override
    public UserResponse addNewUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setPhone(userRequest.getPhone());
        user.setPoint(Double.valueOf(0));
        if(userRequest.getUid() != null){
            RFID rfid = rfidRepository.searchRFIDByUid(userRequest.getUid());
            user.setRfid(rfid);
            rfidService.updateAction(rfid.getId(), user);
        }
        return mapper.userToUserResponse(userRepository.save(user));
    }

    @Override
    public Optional<UserResponse> getUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public String updatePoint(User user, Double point) {
        user.setPoint(point);
        return "done";
    }


}

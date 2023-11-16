package com.example.iot_demo.service.impl;

import com.example.iot_demo.entity.Bill;
import com.example.iot_demo.entity.RFID;
import com.example.iot_demo.entity.User;
import com.example.iot_demo.repository.RFIDRepository;
import com.example.iot_demo.repository.UserRepository;
import com.example.iot_demo.service.RfidService;
import com.example.iot_demo.service.UserService;
import com.example.iot_demo.utils.mapper;
import com.example.iot_demo.utils.request.UserRequest;
import com.example.iot_demo.utils.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        user.setPoint(userRequest.getPoint());
        if(userRequest.getUid() != null){
            RFID rfid = rfidRepository.searchRFIDByUid(userRequest.getUid());
            user.setRfid(rfid);
            rfidService.updateAction(rfid.getId());
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

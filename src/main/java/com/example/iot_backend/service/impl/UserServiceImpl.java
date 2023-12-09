package com.example.iot_backend.service.impl;


import com.example.iot_backend.entity.RFID;
import com.example.iot_backend.entity.User;
import com.example.iot_backend.repository.BillDetailRepository;
import com.example.iot_backend.repository.RFIDRepository;
import com.example.iot_backend.repository.UserRepository;
import com.example.iot_backend.service.BillService;
import com.example.iot_backend.service.RfidService;
import com.example.iot_backend.utils.mapper;
import com.example.iot_backend.utils.request.UserRequest;
import com.example.iot_backend.utils.response.BillResponse;
import com.example.iot_backend.utils.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
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
    public String updatePoint(User user, Double point) {
        user.setPoint(point);
        return "done";
    }

    @Override
    public UserResponse getUserCurrent() {
        User result = new User();
        List<RFID> listRfid = rfidRepository.findAll();
        List<User> userList = userRepository.findAll();
        for(RFID rfid : listRfid){
            if(rfid.isCurrentStatus() == true){
                for(User user : userList){
                    if(!Objects.isNull(user.getRfid())){
                        if(rfid.getId() == user.getRfid().getId()){
                            result = user;
                        }
                    }

                }
            }
        }
        return mapper.userToUserResponse(result);
    }

    @Override
    public List<UserResponse> findUsersByName(String name) {
        return mapper.userToUserResponseList(userRepository.findUsersByName(name).stream().collect(Collectors.toList()));
    }

    @Override
    public List<UserResponse> findUsersByPhone(String phone) {
        return mapper.userToUserResponseList(userRepository.findUsersByPhone(phone).stream().collect(Collectors.toList()));
    }

    @Override
    public List<UserResponse> findUserByProductSold(Long id) {
        return mapper.userToUserResponseList(userRepository.findUserSoldByProductId(id));
    }


}

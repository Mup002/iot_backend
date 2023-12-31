package com.example.iot_backend.service;

import com.example.iot_backend.entity.User;
import com.example.iot_backend.utils.request.RfidRequest;
import com.example.iot_backend.utils.response.RfidResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RfidService {
    String updateAction(Long id, User user);
    List<RfidResponse> getCardIsntAction();

    String changeStatus(String uid);
    String offStatus(String uid);
}

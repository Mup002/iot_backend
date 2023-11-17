package com.example.iot_backend.service;

import com.example.iot_backend.utils.request.RfidRequest;
import com.example.iot_backend.utils.response.RfidResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RfidService {
    String updateAction(Long id);
    List<RfidResponse> getCardIsntAction();

}

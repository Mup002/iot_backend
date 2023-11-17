package com.example.iot_backend.controller;

import com.example.iot_backend.service.RfidService;
import com.example.iot_backend.utils.response.RfidResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rfid")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class RfidController {
    private final RfidService rfidService;
    @GetMapping("/getIsntAction")
    public ResponseEntity<List<RfidResponse>> getList(){
        List<RfidResponse> rfidResponses =  rfidService.getCardIsntAction();
        return new ResponseEntity<>(rfidResponses, HttpStatus.OK);
    }
}

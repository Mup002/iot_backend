package com.example.iot_backend.controller;

import com.example.iot_backend.service.RfidService;
import com.example.iot_backend.utils.response.RfidResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @PostMapping("/change")
    public String changeStatus(@RequestBody Map<String, String> payload){
        String uid = payload.get("uid");
        String change = rfidService.changeStatus(uid);
        return "done";

    }
}

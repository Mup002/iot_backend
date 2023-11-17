package com.example.iot_backend.controller;

import com.example.iot_backend.service.BillService;
import com.example.iot_backend.utils.request.BillRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bill")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class BillController {
    private final BillService billService;
    @PostMapping("/create/{id_user}")
    public ResponseEntity<String> create(@RequestBody BillRequest dto,@PathVariable("id_user") Long id_user){
        String  response = billService.createdBill(dto, id_user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

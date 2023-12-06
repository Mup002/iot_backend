package com.example.iot_backend.controller;


import com.example.iot_backend.service.BillService;
import com.example.iot_backend.utils.request.UserRequest;
import com.example.iot_backend.utils.response.UserResponse;
import com.example.iot_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class UserController {
    @Autowired
    private final UserService userService;
    private final BillService billService;
    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest dto){
        UserResponse userResponse = userService.addNewUser(dto);
        return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<UserResponse>> getAll(){
        List<UserResponse> userResponses = userService.getAllUser();
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }
    @GetMapping("/current")
    public ResponseEntity<UserResponse> current(){
        UserResponse userResponse = userService.getUserCurrent();
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/findUsersByName/{name}")
    public ResponseEntity<List<UserResponse>> findUsersByName(@PathVariable("name") String name){
        List<UserResponse> userResponses = userService.findUsersByName(name);
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @GetMapping("/findUsersByPhone/{phone}")
    public ResponseEntity<List<UserResponse>> findUsersByPhone(@PathVariable("phone") String phone){
        List<UserResponse> userResponses = userService.findUsersByPhone(phone);
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @GetMapping("/findUsersByBillOfDate/{date1}/to/{date2}")
    public ResponseEntity<List<UserResponse>> findUsersByDate(@PathVariable("date1") String date1, @PathVariable("date2") String date2){
        List<UserResponse> userResponses = billService.getUsersByDateOfBill(date1,date2);
        return new ResponseEntity<>(userResponses,HttpStatus.OK);
    }

    @GetMapping("/findUsersByNameInRange/{date1}/to/{date2}/by/{name}")
    public ResponseEntity<List<UserResponse>> findUsersByNameInRange(@PathVariable("date1") String date1, @PathVariable("date2") String date2, @PathVariable("name") String name){
        List<UserResponse> userResponses = billService.getUsersByNameInRange(date1, date2, name);
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @GetMapping("/findUsersByPhoneInRange/{date1}/to/{date2}/by/{phone}")
    public ResponseEntity<List<UserResponse>> findUsersByPhoneInRange(@PathVariable("date1") String date1, @PathVariable("date2") String date2, @PathVariable("phone") String phone){
        List<UserResponse> userResponses = billService.getUsersByPhoneInRange(date1, date2, phone);
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @GetMapping("/findUsersByNamePhoneInRange/{date1}/to/{date2}/by/{name}/and/{phone}")
    public ResponseEntity<List<UserResponse>> findUsersByNamePhoneInRange(@PathVariable("date1") String date1, @PathVariable("date2") String date2, @PathVariable("name") String name, @PathVariable("phone") String phone){
        List<UserResponse> userResponses = billService.getUsersByPhoneAndNameInRange(date1, date2,name, phone);
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }
}

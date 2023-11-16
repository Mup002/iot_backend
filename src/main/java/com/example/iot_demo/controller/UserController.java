package com.example.iot_demo.controller;


import com.example.iot_demo.utils.request.UserRequest;
import com.example.iot_demo.utils.response.UserResponse;
import com.example.iot_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173/")
public class UserController {
    @Autowired
    private final UserService userService;
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
}

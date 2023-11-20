package com.example.iot_backend.controller;


import com.example.iot_backend.utils.request.UserRequest;
import com.example.iot_backend.utils.response.UserResponse;
import com.example.iot_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
}

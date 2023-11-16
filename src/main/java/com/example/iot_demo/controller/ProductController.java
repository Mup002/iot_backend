package com.example.iot_demo.controller;

import com.example.iot_demo.service.ProductService;
import com.example.iot_demo.utils.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class ProductController {
    private final ProductService productService;
    @PutMapping("/update/{id}/{quantity}")
    public ResponseEntity<String> update(@PathVariable("id")Long id, @PathVariable("quantity")Long quantity){
        String response = productService.updateQuantity(id, quantity);
        return new ResponseEntity<>("done", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponse>> getALL(){
        List<ProductResponse> productResponseList = productService.getAllProduct();
        return new ResponseEntity<>(productResponseList,HttpStatus.OK);
    }
}

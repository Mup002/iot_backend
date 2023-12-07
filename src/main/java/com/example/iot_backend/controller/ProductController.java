package com.example.iot_backend.controller;

import com.example.iot_backend.service.BillDetailService;
import com.example.iot_backend.service.BillService;
import com.example.iot_backend.service.ProductService;
import com.example.iot_backend.utils.response.BillDetailCustomResponse;
import com.example.iot_backend.utils.response.BillDetailResponse;
import com.example.iot_backend.utils.response.ProductCustomResponse;
import com.example.iot_backend.utils.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class ProductController {
    private final ProductService productService;
    private final BillService billService;
    private final BillDetailService billDetailService;
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

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Optional<ProductResponse>> getProduct(@PathVariable("id")Long id){
        Optional<ProductResponse> productResponse = productService.getProduct(id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
//    @GetMapping("/product-sales/{saleDate}")
//    public List<ProductCustomResponse> getProductSold(@PathVariable String saleDate) {
//        return productService.getProductSold(saleDate);
//    }

    @GetMapping("/getProductSoldByDate/{date1}/to/{date2}")
    public ResponseEntity<List<BillDetailCustomResponse>> getBillDetails(@PathVariable("date1") String date1, @PathVariable("date2") String date2){
        List<BillDetailCustomResponse> list = billDetailService.getAllproductSoldlByDate(date1, date2);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getProductByIdInRange/{date1}/to/{date2}/by/{id}")
    public ResponseEntity<BillDetailCustomResponse> getProductById(@PathVariable("date1") String date1, @PathVariable("date2") String date2, @PathVariable("id")Long id){
        BillDetailCustomResponse billDetailCustomResponse = billDetailService.getProductByIdInRange(date1, date2, id);
        return new ResponseEntity<>(billDetailCustomResponse,HttpStatus.OK);
    }
    @GetMapping("/getProductByNameInRange/{date1}/to/{date2}/by/{name}")
    public ResponseEntity<List<BillDetailCustomResponse>> getProductByNameInRange(@PathVariable("date1") String date1, @PathVariable("date2") String date2, @PathVariable("name") String name){
        List<BillDetailCustomResponse> billDetailCustomResponseList = billDetailService.getProductsByNameInRange(date1,date2,name);
        return new ResponseEntity<>(billDetailCustomResponseList, HttpStatus.OK);
    }

    @GetMapping("/getProductSoldByName/{name}")
    public ResponseEntity<List<BillDetailCustomResponse>> getProductsByName( @PathVariable("name") String name){
        List<BillDetailCustomResponse> billDetailCustomResponseList = billDetailService.getProductSoldByName(name);
        return new ResponseEntity<>(billDetailCustomResponseList, HttpStatus.OK);
    }

    @GetMapping("/getProductSoldById/{id}")
    public ResponseEntity<BillDetailCustomResponse> getProductById( @PathVariable("id") Long id){
        BillDetailCustomResponse billDetailCustomResponse = billDetailService.getProductSoldById(id);
        return new ResponseEntity<>(billDetailCustomResponse, HttpStatus.OK);
    }
}

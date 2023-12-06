package com.example.iot_backend.controller;

import com.example.iot_backend.entity.Bill;
import com.example.iot_backend.entity.BillDetails;
import com.example.iot_backend.service.BillDetailService;
import com.example.iot_backend.service.BillService;
import com.example.iot_backend.utils.request.BillCustom;
import com.example.iot_backend.utils.request.BillRequest;
import com.example.iot_backend.utils.response.BillDetailCustomResponse;
import com.example.iot_backend.utils.response.BillDetailResponse;
import com.example.iot_backend.utils.response.BillResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bill")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class BillController {
    private final BillService billService;
    private final BillDetailService billDetailService;
    @PostMapping("/create/{id_user}")
    public ResponseEntity<String> create(@RequestBody BillRequest dto,@PathVariable("id_user") Long id_user){
        String  response = billService.createdBill(dto, id_user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createBill(@RequestBody BillCustom dto){
        String response = billService.createGuestBill(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/find/{date1}/to/{date2}")
    public ResponseEntity<List<BillResponse>> find(@PathVariable("date1")String date1, @PathVariable("date2")String date2){
        List<BillResponse>  bill = billService.findByDate(date1, date2);
        return new ResponseEntity<>(bill,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BillResponse>> getAll(){
        List<BillResponse> billResponse = billService.getAllBill();
        return new ResponseEntity<>(billResponse, HttpStatus.OK);
    }

    @GetMapping("/getBillDetailsById/{id}")
    public ResponseEntity<List<BillDetailResponse>> getBill(@PathVariable("id") Long id){
        List<BillDetailResponse> list = billDetailService.getAllBillDetailByIdBill(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getBillDetailByDate/{date1}/to/{date2}")
    public ResponseEntity<List<BillDetailCustomResponse>> getBillDetails(@PathVariable("date1") String date1, @PathVariable("date2") String date2){
        List<BillDetailCustomResponse> list = billDetailService.getAllproductSoldlByDate(date1, date2);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getAllBillOfUserBy/{userid}")
    public ResponseEntity<List<BillResponse>> getAllBillOfUser(@PathVariable("userid") Long userid){
        List<BillResponse> billResponses = billService.getAllBillOfUserByUserId(userid);
        return new ResponseEntity<>(billResponses, HttpStatus.OK);
    }

    @GetMapping("/getBillsOfUserByUserIdInRange/{userid}/in/{date1}/to/{date2}")
    public ResponseEntity<List<BillResponse>> getBillsOfUserInRange(@PathVariable("userid") Long userid, @PathVariable("date1") String date1, @PathVariable("date2") String date2){
        List<BillResponse> billResponses = billService.getBillsOfUserByUserIdInRange(userid, date1, date2);
        return new ResponseEntity<>(billResponses, HttpStatus.OK);
    }
}

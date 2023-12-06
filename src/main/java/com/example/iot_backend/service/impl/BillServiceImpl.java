package com.example.iot_backend.service.impl;

import com.example.iot_backend.entity.Bill;
import com.example.iot_backend.entity.BillDetails;
import com.example.iot_backend.entity.Product;
import com.example.iot_backend.entity.User;
import com.example.iot_backend.repository.BillDetailRepository;
import com.example.iot_backend.repository.BillRepository;
import com.example.iot_backend.repository.ProductRepository;
import com.example.iot_backend.repository.UserRepository;
import com.example.iot_backend.service.BillService;
import com.example.iot_backend.service.ProductService;
import com.example.iot_backend.service.RfidService;
import com.example.iot_backend.utils.mapper;
import com.example.iot_backend.utils.request.BillCustom;
import com.example.iot_backend.utils.request.BillRequest;
import com.example.iot_backend.utils.request.ProductCustom;
import com.example.iot_backend.utils.response.BillDetailResponse;
import com.example.iot_backend.utils.response.BillResponse;
import com.example.iot_backend.utils.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.model.internal.ListBinder;
import org.springframework.stereotype.Service;
import com.example.iot_backend.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final ProductService productService;
    private final RfidService rfidService;
    private final BillDetailRepository billDetailRepository;
    @Override
    public String createdBill(BillRequest dto, Long id_user) {
        Bill bill = new Bill();
        Date created = new Date();

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            created = dateFormat.parse(dto.getCreatedDate());
        }catch (Exception e){
            e.printStackTrace();
        }
        bill.setCreated(created);
        User user = userRepository.findUserById(id_user);
        user.addBill(bill);
        bill.setUser(user);

        Double usedPoint = Double.valueOf(0);
        Double savedPoint = Double.valueOf(0);

        rfidService.offStatus(user.getRfid().getUid());
        Double currentPoint = Double.valueOf(user.getPoint());
        if(currentPoint == 0){
            bill.setTotalPrice(dto.getTotalPrice());
            savedPoint = dto.getTotalPrice() / 100;
            currentPoint  += savedPoint;
        }else{
            if(dto.isUsePoint() == true){
                usedPoint = currentPoint;
                bill.setTotalPrice(dto.getTotalPrice() - currentPoint);
                currentPoint = (double) 0;
            }else{
                bill.setTotalPrice(dto.getTotalPrice());
                savedPoint = dto.getTotalPrice() / 100;
                currentPoint  += savedPoint;
            }
        }
        bill.setPointUsed(usedPoint);
        bill.setPointSaved(savedPoint);
        userService.updatePoint(user,currentPoint);
        billRepository.save(bill);
        List<ProductCustom> productCustomList = dto.getProductCustomList();
        for(ProductCustom p : productCustomList){
            BillDetails billDetails = new BillDetails();
            Product product = productRepository.findProductById(p.getId());
            productService.updateQuantity(p.getId(),p.getQuantity());
            billDetails.setProduct_name(product.getName());
            billDetails.setQuantity_sold(p.getQuantity());
//            billDetails.setPointUse(usedPoint);
            product.addBillDetails(billDetails);
            bill.addBillDetails(billDetails);
            billDetails.setProduct(product);
            billDetailRepository.save(billDetails);
        }
        return "done";
    }

    @Override
    public String createGuestBill(BillCustom dto) {
        Bill bill = new Bill();
        Date created = new Date();

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            created = dateFormat.parse(dto.getCreatedDate());
        }catch (Exception e){
            e.printStackTrace();
        }
        bill.setCreated(created);
        bill.setTotalPrice(dto.getTotalPrice());
        billRepository.save(bill);
        List<ProductCustom> productCustomList = dto.getProductCustomList();
        for(ProductCustom p : productCustomList){
            BillDetails billDetails = new BillDetails();
            Product product = productRepository.findProductById(p.getId());
            productService.updateQuantity(p.getId(),p.getQuantity());
            billDetails.setProduct_name(product.getName());
            billDetails.setQuantity_sold(p.getQuantity());
            billDetails.setBill(bill);
            billDetails.setProduct(product);
            billDetailRepository.save(billDetails);
        }
        return "done";
    }

    @Override
    public List<BillResponse> findByDate(String date1, String date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<BillResponse> billResponseList = new ArrayList<>();
        List<Bill> billList = new ArrayList<>();
        try{
            Date convertdate1 = dateFormat.parse(date1);
            Date convertdate2 = dateFormat.parse(date2);
            billList = billRepository.findBillsByDateRange(convertdate1,convertdate2);
        }catch (ParseException e){
            e.printStackTrace();
        }


        for(Bill b :billList){
            billResponseList.add(mapper.billToBillResponse(b));
        }
        return billResponseList;
    }

    @Override
    public List<BillResponse> getAllBill() {
        return mapper.billToBillResponseList(StreamSupport.stream(billRepository.findAll().spliterator(),false).collect(Collectors.toList()));
    }

    @Override
    public List<UserResponse> getUsersByDateOfBill(String date1, String date2) {
        List<BillResponse> billResponses = findByDate(date1, date2);
        List<UserResponse> result = new ArrayList<>();
        for(BillResponse b : billResponses){
            User user = userRepository.findUserByName(b.getUsername());
            result.add(mapper.userToUserResponse(user));
        }
        return result;
    }

    @Override
    public List<UserResponse> getUsersByNameInRange(String date1, String date2, String name) {
        List<BillResponse> billResponses = findByDate(date1, date2);
        List<UserResponse> result = new ArrayList<>();
        String normalizeInput = StringUtils.stripAccents(name.toLowerCase().trim().replaceAll("\\s+", " "));
        Set<Long> userId = new HashSet<>();
        for(BillResponse b : billResponses){
            String normalize = StringUtils.stripAccents(b.getUsername().toLowerCase());
            if(normalize.contains(normalizeInput)){
                if(userId.isEmpty()){
                    userId.add(userRepository.findUserByName(b.getUsername()).getId());
                    result.add(mapper.userToUserResponse(userRepository.findUserByName(b.getUsername())));
                }else if(!userId.contains(userRepository.findUserByName(b.getUsername()).getId())){
                    userId.add(userRepository.findUserByName(b.getUsername()).getId());
                    result.add(mapper.userToUserResponse(userRepository.findUserByName(b.getUsername())));
                }

            }
        }
        return result;
    }

    @Override
    public List<UserResponse> getUsersByPhoneInRange(String date1, String date2, String phone) {
        List<BillResponse> billResponses = findByDate(date1, date2);
        List<UserResponse> result = new ArrayList<>();
        String normalizeInput = phone.trim().replaceAll("\\s+", " ");
        Set<Long> userId = new HashSet<>();
        for(BillResponse b : billResponses){
            User user = userRepository.findUserByName(b.getUsername());
            if(user.getPhone().contains(normalizeInput)){
                if(userId.isEmpty()){
                    userId.add(user.getId());
                    result.add(mapper.userToUserResponse(user));
                }else if(!userId.contains(user.getId())){
                    userId.add(user.getId());
                    result.add(mapper.userToUserResponse(user));
                }

            }
        }
        return result;
    }

    @Override
    public List<UserResponse> getUsersByPhoneAndNameInRange(String date1, String date2, String name, String phone) {
        List<BillResponse> billResponses = findByDate(date1, date2);
        List<UserResponse> result = new ArrayList<>();
        String normalizeInput1 = phone.trim().replaceAll("\\s+", " ");
        String normalizeInput2 = StringUtils.stripAccents(name.toLowerCase().trim().replaceAll("\\s+", " "));
        Set<Long> userId = new HashSet<>();
        for(BillResponse b : billResponses){
            User user = userRepository.findUserByName(b.getUsername());
            if(user.getPhone().contains(normalizeInput1) && user.getName().contains(normalizeInput2)){
                if(userId.isEmpty()){
                    userId.add(user.getId());
                    result.add(mapper.userToUserResponse(user));
                }else if(!userId.contains(user.getId())){
                    userId.add(user.getId());
                    result.add(mapper.userToUserResponse(user));
                }

            }
        }
        return result;
    }

}

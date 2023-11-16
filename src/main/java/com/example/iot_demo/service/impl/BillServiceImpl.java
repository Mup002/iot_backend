package com.example.iot_demo.service.impl;

import com.example.iot_demo.entity.Bill;
import com.example.iot_demo.entity.Product;
import com.example.iot_demo.entity.User;
import com.example.iot_demo.repository.BillRepository;
import com.example.iot_demo.repository.ProductRepository;
import com.example.iot_demo.repository.UserRepository;
import com.example.iot_demo.service.BillService;
import com.example.iot_demo.utils.request.BillRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.iot_demo.service.UserService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
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


        List<Long> idProductList = dto.getIdProductList();
        for(Long id : idProductList){
            Product p = productRepository.findProductById(id);
            bill.addProduct(p);
            p.addBill(bill);
        }
        User user = userRepository.findUserById(id_user);
        user.addBill(bill);

        Double currentPoint = Double.valueOf(user.getPoint());
        if(currentPoint == 0){
            bill.setTotalPrice(dto.getTotalPrice());
            currentPoint  += dto.getTotalPrice() / 100;
        }else{
            if(dto.isUsePoint() == true){
                bill.setTotalPrice(dto.getTotalPrice() - currentPoint);
                currentPoint = (double) 0;
            }else{
                currentPoint  += dto.getTotalPrice() / 100;
            }
        }
        userService.updatePoint(user,currentPoint);
        bill.setUser(user);
        billRepository.save(bill);
        return "done";
    }
}

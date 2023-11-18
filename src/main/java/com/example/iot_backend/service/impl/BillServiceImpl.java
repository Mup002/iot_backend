package com.example.iot_backend.service.impl;

import com.example.iot_backend.entity.Bill;
import com.example.iot_backend.entity.Product;
import com.example.iot_backend.entity.User;
import com.example.iot_backend.repository.BillRepository;
import com.example.iot_backend.repository.ProductRepository;
import com.example.iot_backend.repository.UserRepository;
import com.example.iot_backend.service.BillService;
import com.example.iot_backend.service.ProductService;
import com.example.iot_backend.utils.request.BillRequest;
import com.example.iot_backend.utils.request.ProductCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.iot_backend.service.UserService;

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
    private final ProductService productService;
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

        List<ProductCustom> productCustomList = dto.getProductCustomList();
        for(ProductCustom p : productCustomList){
            Product product = productRepository.findProductById(p.getId());
            productService.updateQuantity(p.getId(),p.getQuantity());
            bill.addProduct(product);
            product.addBill(bill);
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
                bill.setTotalPrice(dto.getTotalPrice());
                currentPoint  += dto.getTotalPrice() / 100;
            }
        }
        userService.updatePoint(user,currentPoint);
        bill.setUser(user);
        billRepository.save(bill);
        return "done";
    }
}

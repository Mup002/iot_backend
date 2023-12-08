package com.example.iot_backend.utils;



import com.example.iot_backend.entity.*;
import com.example.iot_backend.repository.ProductRepository;
import com.example.iot_backend.utils.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class mapper {
    private final ProductRepository productRepository;
    public static UserResponse userToUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setPhone(user.getPhone());
        userResponse.setPoint(Double.valueOf(user.getPoint()));
        if(user.getRfid() != null){
            userResponse.setUid(user.getRfid().getUid());
        }else{
            userResponse.setUid("No item");
        }

        return userResponse;
    }

    public static List<UserResponse> userToUserResponseList(List<User> users){
        List<UserResponse> userResponses = new ArrayList<>();
        for(User u : users){
            userResponses.add(userToUserResponse(u));
        }
        return userResponses;
    }

    public static ProductResponse productToProductResponse(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId_product(product.getId());
        productResponse.setName_product(product.getName());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }

    public static List<ProductResponse> productResponseList(List<Product> products){
        List<ProductResponse> productResponseList = new ArrayList<>();
        for(Product p : products){
            productResponseList.add(productToProductResponse(p));
        }

        return productResponseList;
    }

    public static RfidResponse rfidToRfidResponse(RFID rfid){
        RfidResponse response = new RfidResponse();
        response.setUid(rfid.getUid());
        return response;
    }
    public static List<RfidResponse> rfidResponses(List<RFID> rfids){
        List<RfidResponse> list = new ArrayList<>();
        for(RFID r : rfids){
            list.add(mapper.rfidToRfidResponse(r));
        }
        return list;
    }

    public static BillResponse billToBillResponse(Bill bill){
        BillResponse response = new BillResponse();
        response.setId(bill.getId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(bill.getCreated());
        response.setCreatedDate(formattedDate);
        response.setTotalPrice(bill.getTotalPrice());
        if(!ObjectUtils.isEmpty(bill.getUser())){
            response.setUsername(bill.getUser().getName());
            response.setUsedPoint(bill.getPointUsed());
            response.setSavedPoint(bill.getPointSaved());
        }else{
            response.setUsername("Guest");
            response.setUsedPoint(Double.valueOf(0));
            response.setSavedPoint(Double.valueOf(0));
        }
        return response;
    }

    public static List<BillResponse> billToBillResponseList(List<Bill> billList){
        List<BillResponse> billResponses = new ArrayList<>();
        for(Bill bill : billList){
            billResponses.add(mapper.billToBillResponse(bill));
        }
        return billResponses;
    }

    public static BillDetailResponse billDetailResponse(BillDetails billDetails){
        BillDetailResponse billDetailResponse = new BillDetailResponse();
        billDetailResponse.setProduct_name(billDetails.getProduct_name());
        billDetailResponse.setQuantity_sold(billDetails.getQuantity_sold());
        billDetailResponse.setId_bill(billDetails.getBill().getId());
        billDetailResponse.setId_product(billDetails.getProduct().getId());
        billDetailResponse.setPrice_unit(billDetails.getPrice_unit());
        billDetailResponse.setPrice_sold(billDetails.getPrice_sold());
        return billDetailResponse;
    }
    public static List<BillDetailResponse> billDetailResponseList(List<BillDetails> billDetails){
        List<BillDetailResponse> billDetailResponseList = new ArrayList<>();
        for(BillDetails bill : billDetails){
            billDetailResponseList.add(mapper.billDetailResponse(bill));
        }
        return billDetailResponseList;
    }
}

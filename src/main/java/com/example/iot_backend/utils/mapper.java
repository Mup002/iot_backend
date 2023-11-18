package com.example.iot_backend.utils;



import com.example.iot_backend.entity.Product;
import com.example.iot_backend.entity.RFID;
import com.example.iot_backend.entity.User;
import com.example.iot_backend.utils.response.ProductResponse;
import com.example.iot_backend.utils.response.RfidResponse;
import com.example.iot_backend.utils.response.UserResponse;

import java.util.ArrayList;
import java.util.List;


public class mapper {
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
}

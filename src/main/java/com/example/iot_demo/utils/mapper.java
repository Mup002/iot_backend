package com.example.iot_demo.utils;

import com.example.iot_demo.entity.Product;
import com.example.iot_demo.entity.User;
import com.example.iot_demo.utils.response.ProductResponse;
import com.example.iot_demo.utils.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class mapper {
    public static UserResponse userToUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setPhone(user.getPhone());
        userResponse.setPhone(user.getPhone());
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
}

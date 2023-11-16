package com.example.iot_demo.utils;

import com.example.iot_demo.entity.User;
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
}

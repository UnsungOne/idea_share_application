package com.idea.share.com.idea.share.dto;

import com.idea.share.com.idea.share.user.User;
import com.idea.share.com.idea.share.user.UserDTO;

public class ModelMapper {

    public static User map(UserDTO userDTO){
        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }
}
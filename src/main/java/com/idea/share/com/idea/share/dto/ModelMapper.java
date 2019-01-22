package com.idea.share.com.idea.share.dto;

import com.idea.share.com.idea.share.idea.Idea;
import com.idea.share.com.idea.share.idea.IdeaDTO;
import com.idea.share.com.idea.share.idea.IdeaRateDTO;
import com.idea.share.com.idea.share.user.User;
import com.idea.share.com.idea.share.user.UserDTO;
import com.idea.share.com.idea.share.user.UserLoginDTO;

public class ModelMapper {


    public ModelMapper() {
    }

    public static Idea map(IdeaDTO ideaDTO) {
        return Idea.builder()
                .id(ideaDTO.getId())
                .title(ideaDTO.getTitle())
                .description(ideaDTO.getDescription())
                .addedAt(ideaDTO.getAddedAt())
                .score(ideaDTO.getScore())
                .user(ideaDTO.getUser())
                .build();
    }

    public static IdeaDTO maoToDTO(Idea idea) {
        return IdeaDTO.builder()
                .id(idea.getId())
                .title(idea.getTitle())
                .description(idea.getDescription())
                .addedAt(idea.getAddedAt())
                .score(idea.getScore())
                .user(idea.getUser())
                .build();
    }



    public static IdeaRateDTO maoToRateDTO(Idea idea) {
        return IdeaRateDTO.builder()
                .id(idea.getId())
                .score(idea.getScore())
                .build();
    }



    public static User mapToUser(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .name(userDTO.getName())
                .build();
    }

    public static UserDTO mapToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .build();
    }

    public static UserLoginDTO mapToUserLoginDTO(User user) {
        return UserLoginDTO.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static User mapToUserLoginDTO(UserLoginDTO userLoginDTO) {
        return User.builder()
                .email(userLoginDTO.getEmail())
                .password(userLoginDTO.getPassword())
                .build();
    }


}
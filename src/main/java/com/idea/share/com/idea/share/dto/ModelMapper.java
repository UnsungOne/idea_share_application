package com.idea.share.com.idea.share.dto;

import com.idea.share.com.idea.share.idea.Idea;
import com.idea.share.com.idea.share.idea.IdeaDTO;
import com.idea.share.com.idea.share.user.User;
import com.idea.share.com.idea.share.user.UserDTO;

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
                .build();
    }

    public static IdeaDTO map(Idea idea) {
        return IdeaDTO.builder()
                .id(idea.getId())
                .title(idea.getTitle())
                .description(idea.getDescription())
                .addedAt(idea.getAddedAt())
                .score(idea.getScore())
                .build();
    }



    public static User map(UserDTO userDTO) {
        return User.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .name(userDTO.getName())
                .build();
    }
}
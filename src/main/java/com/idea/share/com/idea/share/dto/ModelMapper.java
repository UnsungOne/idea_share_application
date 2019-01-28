package com.idea.share.com.idea.share.dto;

import com.idea.share.com.idea.share.idea.Idea;
import com.idea.share.com.idea.share.idea.IdeaDTO;
import com.idea.share.com.idea.share.idea.IdeaEditDTO;
import com.idea.share.com.idea.share.idea.IdeaRateDTO;
import com.idea.share.com.idea.share.user.User;
import com.idea.share.com.idea.share.user.UserDTO;

public class ModelMapper {

    public ModelMapper() {
    }

    public static IdeaDTO mapToIdeaDTOFromIdea(Idea idea) {
        return IdeaDTO.builder()
                .id(idea.getId())
                .title(idea.getTitle())
                .description(idea.getDescription())
                .added(idea.getAdded())
                .score(idea.getScore())
                .user(idea.getUser())
                .build();
    }

    public static IdeaRateDTO maoToRateDTOFromIdea(Idea idea) {
        return IdeaRateDTO.builder()
                .score(idea.getScore())
                .build();
    }

    public static IdeaEditDTO mapToIdeaEditDTOFromIdea(Idea idea) {
        return IdeaEditDTO.builder()
                .id(idea.getId())
                .title(idea.getTitle())
                .description(idea.getDescription())
                .build();
    }

    public static User mapToUserFromUserDTO(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .name(userDTO.getName())
                .build();
    }
}
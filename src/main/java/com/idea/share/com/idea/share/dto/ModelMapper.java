package com.idea.share.com.idea.share.dto;

import com.idea.share.com.idea.share.idea.Idea;
import com.idea.share.com.idea.share.idea.IdeaDTO;

public class ModelMapper {


    public ModelMapper() {
    }

    public static Idea map(IdeaDTO ideaDTO){
        return Idea.builder()
                .id(ideaDTO.getId())
                .title(ideaDTO.getTitle())
                .description(ideaDTO.getDescription())
                .addedAt(ideaDTO.getAddedAt())
                .build();
    }
}
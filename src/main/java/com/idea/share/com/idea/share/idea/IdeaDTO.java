package com.idea.share.com.idea.share.idea;


import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class IdeaDTO  {

    private Integer id;
    private String title;
    private String description;
    private LocalDateTime addedAt;
    private int score;
    private boolean active;

    public IdeaDTO(String title, String description, LocalDateTime addedAt, int score, boolean active) {
        this.title = title;
        this.description = description;
        this.addedAt = addedAt;
        this.score = score;
        this.active = active;
    }
}

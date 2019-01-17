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

}

package com.idea.share.com.idea.share.idea;


import com.idea.share.com.idea.share.user.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdeaDTO  {

    private Integer id;
    private String title;
    private String description;
    private LocalDateTime addedAt;
    private int score;
    private boolean active;
    private User user;


}

package com.idea.share.com.idea.share.idea;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ideas")
public class Idea {
    @Id
    private Long id;
    private String title;
    private String description;
    private LocalDateTime addedAt;
    private int score;
    private boolean active;
}

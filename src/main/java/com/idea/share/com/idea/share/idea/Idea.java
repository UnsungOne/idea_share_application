package com.idea.share.com.idea.share.idea;

import com.idea.share.com.idea.share.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "ideas")
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime addedAt;
    private int score;
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;


    public Idea(String title, String description, LocalDateTime addedAt, int score, boolean active) {
        this.title = title;
        this.description = description;
        this.addedAt = addedAt;
        this.score = score;
        this.active = active;
    }
}
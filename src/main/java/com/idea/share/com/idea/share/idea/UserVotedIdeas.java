package com.idea.share.com.idea.share.idea;

import com.idea.share.com.idea.share.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_voted_ideas")
public class UserVotedIdeas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idea_id")
    private Idea idea;

}
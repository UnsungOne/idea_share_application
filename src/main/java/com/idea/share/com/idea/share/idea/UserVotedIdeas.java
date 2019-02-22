package com.idea.share.com.idea.share.idea;

import com.idea.share.com.idea.share.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class UserVotedIdeas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iduser")
    private User user;

    @ManyToMany
    @JoinTable (name = "user_voted_ideas",
            joinColumns = {@JoinColumn (name = "id_user")},
            inverseJoinColumns = {@JoinColumn(name = "id_idea")})
    private List<Idea> ideas = new ArrayList<>();

}

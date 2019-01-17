package com.idea.share.com.idea.share.user;

import com.idea.share.com.idea.share.idea.Idea;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {
    @Id
    private Integer id;
    private String name;
    private String email;
    private String password;
//    @OneToMany(mappedBy = "user")
//    private List<Idea> ideas = new ArrayList<>();

}
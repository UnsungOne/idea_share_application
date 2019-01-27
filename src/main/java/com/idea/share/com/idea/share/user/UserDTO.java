package com.idea.share.com.idea.share.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private String repeatPassword;

}
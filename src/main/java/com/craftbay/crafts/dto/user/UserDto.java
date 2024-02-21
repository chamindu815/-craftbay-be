package com.craftbay.crafts.dto.user;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String userId;
    private String name;
    private String email;
    private String phone;
    private String password;

}

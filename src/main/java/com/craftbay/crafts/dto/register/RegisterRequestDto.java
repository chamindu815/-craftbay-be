package com.craftbay.crafts.dto.register;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}

package com.craftbay.crafts.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private int id;
    private String username;
    private String role;
    private String firstName;
    private String lastName;
}

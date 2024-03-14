package com.craftbay.crafts.dto.forgotpassword;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordRequestDto {
    private String email;
    private String password;
}

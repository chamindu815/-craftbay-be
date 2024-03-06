package com.craftbay.crafts.dto.register;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterRequestDto {

    @Valid

    @NotNull(message = "email is mandatory")
    @NotBlank(message = "email is mandatory")
    @Email(message = "email should be valid")
    private String username;

    @NotNull(message = "password is mandatory")
    @NotBlank(message = "password is mandatory")
    @Size(min = 5, max = 10, message = "Password must be between 5 and 10 characters")
    private String password;


    private String firstName;
    private String lastName;
    @NotNull(message = "Phone Number is mandatory")
    @NotBlank(message = "Phone Number is mandatory")
    private String phoneNumber;
//    private String stripeAccountId;
//    private String paymentMethodId;
}

package com.craftbay.crafts.util;

import com.craftbay.crafts.dto.register.RegisterRequestDto;
import com.craftbay.crafts.dto.user.UserResponseDto;
import com.craftbay.crafts.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {

    @Autowired
    private static BCryptPasswordEncoder passwordEncoder;

//    public UserUtil(BCryptPasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    public static User convertUserRegisterRequestDtoToUser(RegisterRequestDto request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return user;
    }

    public static UserResponseDto convertUserToUserResponseDto(User user){
        UserResponseDto userResponse = new UserResponseDto();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setRole(user.getRole());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setHouseNo(user.getHouseNo());
        userResponse.setStreetName(user.getStreetName());
        userResponse.setCity(user.getCity());
        userResponse.setCountry(user.getCountry());
        userResponse.setPhoneNo(user.getPhoneNo());
        userResponse.setDateOfBirth(user.getDateOfBirth());
        return userResponse;
    }

}

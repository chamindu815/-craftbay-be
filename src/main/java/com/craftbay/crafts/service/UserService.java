package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.user.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    public UserDto createUser(UserDto userDto);
    public UserDto getUser(String email);

//    String addUser(UserDto userDto);
//    LoginResponse loginUser(LoginDto loginDto);




}

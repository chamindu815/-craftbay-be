package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.login.LoginDto;
import com.craftbay.crafts.dto.user.UserDto;
import com.craftbay.crafts.response.LoginResponse;


public interface UserService {
    String addUser(UserDto userDto);

    LoginResponse loginUser(LoginDto loginDto);
}

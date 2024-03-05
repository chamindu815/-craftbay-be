package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.register.RegisterRequestDto;
import com.craftbay.crafts.dto.user.UserResponseDto;
import com.craftbay.crafts.entity.user.User;

public interface UserService {
    String registerUser(RegisterRequestDto request);

    User getUserByUsername(String username);

    UserResponseDto getUserById(int userId);
}

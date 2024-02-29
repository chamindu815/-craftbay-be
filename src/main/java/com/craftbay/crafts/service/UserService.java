package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.register.RegisterRequestDto;

public interface UserService {
    String registerUser(RegisterRequestDto request);
}

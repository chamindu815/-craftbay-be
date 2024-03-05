package com.craftbay.crafts.service.impl;

import com.craftbay.crafts.dto.register.RegisterRequestDto;
import com.craftbay.crafts.dto.user.UserResponseDto;
import com.craftbay.crafts.entity.user.User;
import com.craftbay.crafts.repository.UserRepository;
import com.craftbay.crafts.service.UserService;
import com.craftbay.crafts.util.ProductUtil;
import com.craftbay.crafts.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String registerUser(RegisterRequestDto request) {
        User user = convertUserRegisterRequestDtoToUser(request);
        User savedUser = userRepository.save(user);
        return savedUser.getUsername();
    }

    public User convertUserRegisterRequestDtoToUser(RegisterRequestDto request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return user;
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public UserResponseDto getUserById(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        UserResponseDto userResponseDto = UserUtil.convertUserToUserResponseDto(user);
        return userResponseDto;
    }
}

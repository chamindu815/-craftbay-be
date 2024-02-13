package com.craftbay.crafts.service.impl;

import com.craftbay.crafts.dto.login.LoginDto;
import com.craftbay.crafts.dto.user.UserDto;
import com.craftbay.crafts.entity.User;
import com.craftbay.crafts.repository.UserRepository;
import com.craftbay.crafts.response.LoginResponse;
import com.craftbay.crafts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserIMPL implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(UserDto userDto) {

        User user = new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPhone(),
                this.passwordEncoder.encode(userDto.getPassword())
        );

        userRepository.save(user);

        return user.getName();
    }

    @Override
    public LoginResponse loginUser(LoginDto loginDto) {
        String msg = "";
        User user1 = userRepository.findByEmail(loginDto.getEmail());
        if (user1 != null) {
            String password = loginDto.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user = userRepository.findOneByEmailAndPassword(loginDto.getEmail(), encodedPassword);
                if (user.isPresent()) {
                    return new LoginResponse("Login Success", true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("Email not exits", false);
            }
        }else {
            return new LoginResponse("Email not exits", false);
        }

    }

}

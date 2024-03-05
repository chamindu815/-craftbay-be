package com.craftbay.crafts.controller;

import com.craftbay.crafts.dto.user.UserResponseDto;
import com.craftbay.crafts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public UserResponseDto getUserById(@PathVariable("userId") int userId){
        return userService.getUserById(userId);
    }
}

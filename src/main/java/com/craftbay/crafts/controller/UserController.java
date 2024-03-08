package com.craftbay.crafts.controller;

import com.craftbay.crafts.dto.card.AddCardDetailsRequest;
import com.craftbay.crafts.dto.user.UpdateUserRequestDto;
import com.craftbay.crafts.dto.user.UserResponseDto;
import com.craftbay.crafts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping("/{userId}")
    public UserResponseDto getUserById(@PathVariable("userId") int userId){
        return userService.getUserById(userId);
    }

    @CrossOrigin
    @PutMapping("/{userId}")
    public UserResponseDto updateUser(@PathVariable("userId") int userId, @RequestBody UpdateUserRequestDto request) throws Exception {
        return userService.updateUser(userId, request);
    }

    @PostMapping("/{userId}/add-card")
    public void addCardToUser(@PathVariable("userId") int userId , @RequestBody AddCardDetailsRequest request) {
        userService.addCardToUser(userId, request);
    }
}

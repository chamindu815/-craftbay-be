package com.craftbay.crafts.controller;

import com.craftbay.crafts.dto.card.AddCardDetailsRequest;
import com.craftbay.crafts.dto.card.CardDetailsResponseDto;
import com.craftbay.crafts.dto.card.UpdateCardRequest;
import com.craftbay.crafts.dto.user.UpdateUserRequestDto;
import com.craftbay.crafts.dto.user.UserResponseDto;
import com.craftbay.crafts.entity.paymentmethod.PaymentMethod;
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

    @CrossOrigin
    @PostMapping("/{userId}/add-card")
    public void addCardToUser(@PathVariable("userId") int userId , @RequestBody AddCardDetailsRequest request) {
        userService.addCardToUser(userId, request);
    }
    @CrossOrigin
    @GetMapping("/{userId}/get-card")
    public CardDetailsResponseDto getCardDetails(@PathVariable("userId") int userId) throws Exception {
        return userService.getCardDetailsByUser(userId);
    }

    @CrossOrigin
    @GetMapping("/{userId}/update-card")
    public CardDetailsResponseDto updateCardDetails(@PathVariable("userId") int userId, @RequestBody UpdateCardRequest request) throws Exception {
        return userService.updateCardDetails(userId, request);
    }
}

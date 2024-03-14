package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.card.AddCardDetailsRequest;
import com.craftbay.crafts.dto.card.CardDetailsResponseDto;
import com.craftbay.crafts.dto.card.UpdateCardRequest;
import com.craftbay.crafts.dto.forgotpassword.ForgotPasswordRequestDto;
import com.craftbay.crafts.dto.register.RegisterRequestDto;
import com.craftbay.crafts.dto.user.UpdateUserRequestDto;
import com.craftbay.crafts.dto.user.UserResponseDto;
import com.craftbay.crafts.entity.user.User;

public interface UserService {
    String registerUser(RegisterRequestDto request);

    User getUserByUsername(String username);

    UserResponseDto getUserById(int userId);

    void addCardToUser(int userId, AddCardDetailsRequest request);

    UserResponseDto updateUser(int userId, UpdateUserRequestDto request) throws Exception;

    CardDetailsResponseDto getCardDetailsByUser(int userId) throws Exception;

    CardDetailsResponseDto updateCardDetails(int userId, UpdateCardRequest request) throws Exception;

    void forgotPassword(ForgotPasswordRequestDto request);
}

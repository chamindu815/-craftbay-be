package com.craftbay.crafts.dto.card;

import com.craftbay.crafts.dto.user.UserResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDetailsResponseDto {

    private String cardNo;
    private String month;
    private String year;
    private String cvv;
    private UserResponseDto user;

}

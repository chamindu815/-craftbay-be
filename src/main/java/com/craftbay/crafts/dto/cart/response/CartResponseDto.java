package com.craftbay.crafts.dto.cart.response;

import com.craftbay.crafts.dto.user.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDto {
    private int id;
    private List<CartItemResponseDto> cartItems;
    private boolean isOrdered;
    private UserResponseDto user;
}

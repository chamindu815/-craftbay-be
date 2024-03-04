package com.craftbay.crafts.dto.cart;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CartRequestDto {
    private int id;

    private List<CartItemRequestDto> cartItems;

    private int userId;
}

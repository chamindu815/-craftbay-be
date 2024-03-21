package com.craftbay.crafts.dto.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRateRequestDto {
    private int cartItemId;
    private int rate;
}

package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.cart.AddToCartRequestDto;
import com.craftbay.crafts.dto.cart.CartRequestDto;

public interface CartService {
    String addToCart(AddToCartRequestDto request) throws Exception;

    String updateCart(CartRequestDto request) throws Exception;
}

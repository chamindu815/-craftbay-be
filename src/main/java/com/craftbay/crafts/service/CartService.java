package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.cart.AddToCartRequestDto;
import com.craftbay.crafts.dto.cart.CartRequestDto;
import com.craftbay.crafts.entity.cart.Cart;

public interface CartService {
    String addToCart(AddToCartRequestDto request) throws Exception;

    String updateCart(CartRequestDto request) throws Exception;

    Cart findCartByUser(int userId) throws Exception;
}

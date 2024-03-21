package com.craftbay.crafts.util;

import com.craftbay.crafts.dto.cart.response.CartItemResponseDto;
import com.craftbay.crafts.entity.cart.Cart;
import com.craftbay.crafts.entity.cart.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemUtil {
    public static CartItemResponseDto convertCartItemToCartItemResponseDto(CartItem cartItem){
        CartItemResponseDto cartItemResponse = new CartItemResponseDto();
        cartItemResponse.setId(cartItem.getId());
        cartItemResponse.setQuantity(cartItem.getQuantity());
        cartItemResponse.setProduct(ProductUtil.convertProductToProductResponseDto(cartItem.getProduct()));
        cartItemResponse.setRate(cartItem.getRate());
        return cartItemResponse;
    }
}

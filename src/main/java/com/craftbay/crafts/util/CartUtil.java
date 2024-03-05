package com.craftbay.crafts.util;

import com.craftbay.crafts.dto.cart.response.CartItemResponseDto;
import com.craftbay.crafts.dto.cart.response.CartResponseDto;
import com.craftbay.crafts.entity.cart.Cart;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartUtil {

    public static CartResponseDto covertCartToCartResponseDto(Cart cart){
        CartResponseDto cartResponse = new CartResponseDto();
        cartResponse.setId(cart.getId());
        cartResponse.setOrdered(cart.isOrdered());
        List<CartItemResponseDto> cartItemResponseDtoList = new ArrayList<>();
        for (int i =0; i<cart.getCartItems().size(); i++){
            CartItemResponseDto cartItemResponse = CartItemUtil.convertCartItemToCartItemResponseDto(cart.getCartItems().get(i));
            cartItemResponseDtoList.add(cartItemResponse);
        }
        cartResponse.setCartItems(cartItemResponseDtoList);
        cartResponse.setUser(UserUtil.convertUserToUserResponseDto(cart.getUser()));
        return cartResponse;
    }
}

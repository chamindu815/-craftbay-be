package com.craftbay.crafts.controller;

import com.craftbay.crafts.dto.cart.AddToCartRequestDto;
import com.craftbay.crafts.dto.cart.CartRequestDto;
import com.craftbay.crafts.dto.cart.response.CartResponseDto;
import com.craftbay.crafts.entity.cart.Cart;
import com.craftbay.crafts.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class CartController {

    @Autowired
    private CartService cartService;

    @CrossOrigin
    @PostMapping("/cart/addtocart")
    public String addToCart(@RequestBody AddToCartRequestDto request) throws Exception {
        return cartService.addToCart(request);
    }

    @CrossOrigin
    @PutMapping("/cart/update-cart")
    public String updateCart(@RequestBody CartRequestDto request) throws Exception {
        return cartService.updateCart(request);
    }

    @CrossOrigin
    @GetMapping("/{userId}/cart")
    public CartResponseDto findCartByUserId(@PathVariable("userId") int userId) throws Exception {
        return cartService.findCartByUser(userId);
    }

}

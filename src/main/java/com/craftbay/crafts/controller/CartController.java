package com.craftbay.crafts.controller;

import com.craftbay.crafts.dto.cart.AddToCartRequestDto;
import com.craftbay.crafts.dto.cart.CartRequestDto;
import com.craftbay.crafts.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addtocart")
    public String addToCart(@RequestBody AddToCartRequestDto request) throws Exception {
        return cartService.addToCart(request);
    }

    @PutMapping("/update-cart")
    public String updateCart(@RequestBody CartRequestDto request) throws Exception {
        return cartService.updateCart(request);
    }
}

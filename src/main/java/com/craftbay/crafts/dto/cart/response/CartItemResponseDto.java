package com.craftbay.crafts.dto.cart.response;

import com.craftbay.crafts.dto.pub.product.ProductResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDto {
    private int id;
    private int quantity;
    private ProductResponseDto product;
}

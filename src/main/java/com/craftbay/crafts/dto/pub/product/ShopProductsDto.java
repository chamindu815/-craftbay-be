package com.craftbay.crafts.dto.pub.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShopProductsDto {
    List<ProductResponseDto> woodenProducts;
    List<ProductResponseDto> metalProducts;
    List<ProductResponseDto> textileProducts;
    List<ProductResponseDto> clayProducts;
    List<ProductResponseDto> leatherProducts;
}

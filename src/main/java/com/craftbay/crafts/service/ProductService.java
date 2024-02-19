package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.pub.product.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getNewArrival();

    List<ProductResponseDto> getShopProducts(String category);
}

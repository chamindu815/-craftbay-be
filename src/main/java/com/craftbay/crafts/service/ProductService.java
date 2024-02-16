package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.product.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getNewArrival();
}

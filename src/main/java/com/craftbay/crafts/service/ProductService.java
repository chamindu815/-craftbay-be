package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.product.AdminProductResponseDto;

import java.util.List;

public interface ProductService {
    List<AdminProductResponseDto> getNewArrival();
}

package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.pub.product.ProductResponseDto;
import com.craftbay.crafts.util.enums.ProductCategoryEnum;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getNewArrival();

    List<ProductResponseDto> getShopProducts(ProductCategoryEnum category);

    List<ProductResponseDto> getAllProductsByCategory(ProductCategoryEnum category);
}

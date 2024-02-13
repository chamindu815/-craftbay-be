package com.craftbay.crafts.util;

import com.craftbay.crafts.dto.product.ProductRequestDto;
import com.craftbay.crafts.dto.product.ProductResponseDto;
import com.craftbay.crafts.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductUtil {
    public static ProductResponseDto convertProductToProductResponseDto(Product product) {
        ProductResponseDto response = new ProductResponseDto();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setImage(product.getImage());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setCategory(product.getCategory());
        return response;
    }

    public static Product convertProductRequestDtoToProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setImage(productRequestDto.getImage());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        product.setCategory(productRequestDto.getCategory());
        return product;
    }

}

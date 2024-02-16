package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.product.UpdateProductRequestDto;
import com.craftbay.crafts.dto.product.ProductResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface AdminProductService {
    ProductResponseDto saveProduct(MultipartFile image, String name, String description, double buyingPrice, double sellingPrice, int quantity, LocalDate date, String category);

    ProductResponseDto updateProduct(UpdateProductRequestDto updateProductRequestDto);

    String deleteProduct(int id);

    List<ProductResponseDto> getProducts();

    ProductResponseDto getProductById(int id);

    ProductResponseDto getProductByName(String name);

    List<ProductResponseDto> adminViewProduct();

}

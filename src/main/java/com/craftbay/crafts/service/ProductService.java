package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.product.ProductResponseDto;
import com.craftbay.crafts.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    ProductResponseDto saveProduct(MultipartFile image, String name, String description, double price, int quantity, String category);

    ProductResponseDto updateProduct(Product product);

    String deleteProduct(int id);

    List<ProductResponseDto> getProducts();

    ProductResponseDto getProductById(int id);

    ProductResponseDto getProductByName(String name);
}

package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.product.AdminUpdateProductRequestDto;
import com.craftbay.crafts.dto.product.AdminProductResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface AdminProductService {
    AdminProductResponseDto adminSaveProduct(MultipartFile image, String name, String description, double buyingPrice, double sellingPrice, int quantity, LocalDate date, String category);

    AdminProductResponseDto adminUpdateProduct(AdminUpdateProductRequestDto adminUpdateProductRequestDto);

    String adminDeleteProduct(int id);

    List<AdminProductResponseDto> adminGetProducts();

    AdminProductResponseDto adminGetProductById(int id);

    AdminProductResponseDto adminGetProductByName(String name);

    List<AdminProductResponseDto> adminViewProducts();

}

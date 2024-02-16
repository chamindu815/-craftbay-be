package com.craftbay.crafts.service.impl;

import com.craftbay.crafts.dto.product.AdminProductResponseDto;
import com.craftbay.crafts.entity.product.Product;
import com.craftbay.crafts.repository.ProductRepository;
import com.craftbay.crafts.service.ProductService;
import com.craftbay.crafts.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<AdminProductResponseDto> getNewArrival(){
        List<Product> productList = productRepository.findAll();
        List<AdminProductResponseDto> adminProductResponseDtoList = new ArrayList<>();
        for (int i=0; i<productList.size(); i++){
            AdminProductResponseDto adminProductResponseDto = ProductUtil.convertProductToAdminProductResponseDto(productList.get(i));
            adminProductResponseDtoList.add(adminProductResponseDto);
        }
        return adminProductResponseDtoList;
    }
}

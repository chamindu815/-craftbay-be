package com.craftbay.crafts.service.impl;

import com.craftbay.crafts.dto.pub.product.ProductResponseDto;
import com.craftbay.crafts.entity.product.Product;
import com.craftbay.crafts.repository.ProductRepository;
import com.craftbay.crafts.service.ProductService;
import com.craftbay.crafts.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<ProductResponseDto> getNewArrival(){

        List<Product> productList = productRepository.  findTop12ByOrderByUpdateDateDesc();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for (int i=0; i<productList.size(); i++){
            ProductResponseDto productResponseDto = ProductUtil.convertProductToProductResponseDto(productList.get(i));
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> getShopProducts(String category) {
        List<Product> products = productRepository.findTop5ByCategory(category);
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for (int i =0; i<products.size(); i++){
            ProductResponseDto productResponseDto = ProductUtil.convertProductToProductResponseDto(products.get(i));
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;
    }
}

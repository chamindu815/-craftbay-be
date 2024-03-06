package com.craftbay.crafts.service.impl;

import com.craftbay.crafts.dto.pub.product.ProductResponseDto;
import com.craftbay.crafts.dto.pub.product.ShopProductsDto;
import com.craftbay.crafts.entity.product.Product;
import com.craftbay.crafts.repository.ProductRepository;
import com.craftbay.crafts.service.ProductService;
import com.craftbay.crafts.util.ProductUtil;
import com.craftbay.crafts.util.enums.ProductCategoryEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<ProductResponseDto> getNewArrival(){

        List<Product> productList = productRepository.findTop12ByOrderByUpdateDateDesc();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for (int i=0; i<productList.size(); i++){
            ProductResponseDto productResponseDto = ProductUtil.convertProductToProductResponseDto(productList.get(i));
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> getShopProducts(ProductCategoryEnum category) {
        List<Product> products = productRepository.findTop5ByCategory(category);
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for (int i =0; i<products.size(); i++){
            ProductResponseDto productResponseDto = ProductUtil.convertProductToProductResponseDto(products.get(i));
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> getAllProductsByCategory(ProductCategoryEnum category) {
        List<Product> products = productRepository.findAllByCategory(category);
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for (int i=0; i<products.size(); i++){
            ProductResponseDto productResponseDto = ProductUtil.convertProductToProductResponseDto(products.get(i));
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;
    }

    @Override
    public ProductResponseDto getProductById(int productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product product = optionalProduct.get();
        ProductResponseDto productResponseDto = ProductUtil.convertProductToProductResponseDto(product);
        return productResponseDto;
    }

    @Override
    public ShopProductsDto getShopProducts() {
        List<ProductResponseDto> woodProducts =
                ProductUtil.convertListOfProductsToProductResponseDtos(productRepository.findTop5ByCategory(ProductCategoryEnum.WOODEN));
        List<ProductResponseDto> metalProducts =
                ProductUtil.convertListOfProductsToProductResponseDtos(productRepository.findTop5ByCategory(ProductCategoryEnum.METAL));
        List<ProductResponseDto> textileProducts =
                ProductUtil.convertListOfProductsToProductResponseDtos(productRepository.findTop5ByCategory(ProductCategoryEnum.TEXTILE));
        List<ProductResponseDto> clayProducts =
                ProductUtil.convertListOfProductsToProductResponseDtos(productRepository.findTop5ByCategory(ProductCategoryEnum.CLAY));
        List<ProductResponseDto> leatherProducts =
                ProductUtil.convertListOfProductsToProductResponseDtos(productRepository.findTop5ByCategory(ProductCategoryEnum.LEATHER));

        ShopProductsDto response = new ShopProductsDto();
        response.setWoodenProducts(woodProducts);
        response.setMetalProducts(metalProducts);
        response.setClayProducts(clayProducts);
        response.setTextileProducts(textileProducts);
        response.setLeatherProducts(leatherProducts);

        return response;
    }


}

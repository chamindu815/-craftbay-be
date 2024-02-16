package com.craftbay.crafts.util;

import com.craftbay.crafts.dto.product.ProductBuyingPriceDetailsDto;
import com.craftbay.crafts.dto.product.ProductResponseDto;
import com.craftbay.crafts.dto.product.ProductSellingPriceDetailsDto;
import com.craftbay.crafts.entity.product.Product;
import com.craftbay.crafts.entity.product.ProductBuyingPriceDetails;
import com.craftbay.crafts.entity.product.ProductSellingPriceDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductUtil {
    public static ProductResponseDto convertProductToProductResponseDto(Product product) {
        ProductResponseDto response = new ProductResponseDto();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setImage(product.getImage());
        response.setDescription(product.getDescription());
        response.setRemainingQuantity(product.getRemainingQuantity());


        List<ProductBuyingPriceDetailsDto> productBuyingPriceDetailsDtoList = new ArrayList<>();
        for (int i =0; i< product.getProductBuyingPriceDetails().size(); i++){
            ProductBuyingPriceDetails buyingPriceDetails = product.getProductBuyingPriceDetails().get(i);

            ProductBuyingPriceDetailsDto buyingPriceDetailsDto = new ProductBuyingPriceDetailsDto();
            buyingPriceDetailsDto.setId(buyingPriceDetails.getId());
            buyingPriceDetailsDto.setDate(buyingPriceDetails.getDate());
            buyingPriceDetailsDto.setPrice(buyingPriceDetails.getPrice());
            buyingPriceDetailsDto.setQuantity(buyingPriceDetails.getQuantity());
            productBuyingPriceDetailsDtoList.add(buyingPriceDetailsDto);
        }
        response.setProductBuyingPriceDetailsDtos(productBuyingPriceDetailsDtoList);


        List<ProductSellingPriceDetailsDto> productSellingPriceDetailsDtoList = new ArrayList<>();
        for (int i = 0; i<product.getProductSellingPriceDetails().size(); i++){
            ProductSellingPriceDetails sellingPriceDetails = product.getProductSellingPriceDetails().get(i);

            ProductSellingPriceDetailsDto sellingPriceDetailsDto = new ProductSellingPriceDetailsDto();
            sellingPriceDetailsDto.setId(sellingPriceDetails.getId());
            sellingPriceDetailsDto.setDate(sellingPriceDetails.getDate());
            sellingPriceDetailsDto.setPrice(sellingPriceDetails.getPrice());

            productSellingPriceDetailsDtoList.add(sellingPriceDetailsDto);
        }
        response.setProductSellingPriceDetailsDtos(productSellingPriceDetailsDtoList);


        response.setCategory(product.getCategory());
        return response;
    }

//    public static Product convertProductRequestDtoToProduct(ProductRequestDto productRequestDto) {
//        Product product = new Product();
//        product.setName(productRequestDto.getName());
//        product.setImage(productRequestDto.getImage());
//        product.setDescription(productRequestDto.getDescription());
//        product.setCategory(productRequestDto.getCategory());
//        return product;
//    }

}

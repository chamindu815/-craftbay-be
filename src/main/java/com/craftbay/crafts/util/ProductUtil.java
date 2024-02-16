package com.craftbay.crafts.util;

import com.craftbay.crafts.dto.product.AdminProductBuyingPriceDetailsDto;
import com.craftbay.crafts.dto.product.AdminProductResponseDto;
import com.craftbay.crafts.dto.product.AdminProductSellingPriceDetailsDto;
import com.craftbay.crafts.entity.product.Product;
import com.craftbay.crafts.entity.product.ProductBuyingPriceDetails;
import com.craftbay.crafts.entity.product.ProductSellingPriceDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductUtil {
    public static AdminProductResponseDto convertProductToProductResponseDto(Product product) {
        AdminProductResponseDto response = new AdminProductResponseDto();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setImage(product.getImage());
        response.setDescription(product.getDescription());
        response.setRemainingQuantity(product.getRemainingQuantity());


        List<AdminProductBuyingPriceDetailsDto> adminProductBuyingPriceDetailsDtoList = new ArrayList<>();
        for (int i =0; i< product.getProductBuyingPriceDetails().size(); i++){
            ProductBuyingPriceDetails buyingPriceDetails = product.getProductBuyingPriceDetails().get(i);

            AdminProductBuyingPriceDetailsDto buyingPriceDetailsDto = new AdminProductBuyingPriceDetailsDto();
            buyingPriceDetailsDto.setId(buyingPriceDetails.getId());
            buyingPriceDetailsDto.setDate(buyingPriceDetails.getDate());
            buyingPriceDetailsDto.setPrice(buyingPriceDetails.getPrice());
            buyingPriceDetailsDto.setQuantity(buyingPriceDetails.getQuantity());
            adminProductBuyingPriceDetailsDtoList.add(buyingPriceDetailsDto);
        }
        response.setAdminProductBuyingPriceDetailsDtos(adminProductBuyingPriceDetailsDtoList);


        List<AdminProductSellingPriceDetailsDto> adminProductSellingPriceDetailsDtoList = new ArrayList<>();
        for (int i = 0; i<product.getProductSellingPriceDetails().size(); i++){
            ProductSellingPriceDetails sellingPriceDetails = product.getProductSellingPriceDetails().get(i);

            AdminProductSellingPriceDetailsDto sellingPriceDetailsDto = new AdminProductSellingPriceDetailsDto();
            sellingPriceDetailsDto.setId(sellingPriceDetails.getId());
            sellingPriceDetailsDto.setDate(sellingPriceDetails.getDate());
            sellingPriceDetailsDto.setPrice(sellingPriceDetails.getPrice());

            adminProductSellingPriceDetailsDtoList.add(sellingPriceDetailsDto);
        }
        response.setAdminProductSellingPriceDetailsDtos(adminProductSellingPriceDetailsDtoList);


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

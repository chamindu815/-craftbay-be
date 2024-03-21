package com.craftbay.crafts.util;

import com.craftbay.crafts.dto.product.AdminProductBuyingPriceDetailsDto;
import com.craftbay.crafts.dto.product.AdminProductResponseDto;
import com.craftbay.crafts.dto.product.AdminProductSellingPriceDetailsDto;
import com.craftbay.crafts.dto.pub.product.ProductResponseDto;
import com.craftbay.crafts.entity.product.Product;
import com.craftbay.crafts.entity.product.ProductBuyingPriceDetails;
import com.craftbay.crafts.entity.product.ProductSellingPriceDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class ProductUtil {
    public static AdminProductResponseDto convertProductToAdminProductResponseDto(Product product) {
        AdminProductResponseDto response = new AdminProductResponseDto();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setImage(product.getImage());
        response.setDescription(product.getDescription());
        response.setRemainingQuantity(product.getRemainingQuantity());
        response.setRate(product.getRate());
        response.setNoOfRatings(product.getNoOfRatings());


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

    public static ProductResponseDto convertProductToProductResponseDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setCategory(product.getCategory());
        productResponseDto.setRemainingQuantity(product.getRemainingQuantity());
        productResponseDto.setImage(product.getImage());
        productResponseDto.setRate(product.getRate());
        productResponseDto.setNoOfRatings(product.getNoOfRatings());


        LocalDate today = LocalDate.now();
        ProductSellingPriceDetails latestBeforeToday = product.getProductSellingPriceDetails().stream()
                .filter(obj -> ((obj.getDate().isBefore(today))||(obj.getDate().isEqual(today))))
                .max(Comparator.comparing(ProductSellingPriceDetails::getDate))
                .orElse(null);

//        TODO: Need to test properly
//        List<ProductSellingPriceDetails> priceList = product.getProductSellingPriceDetails().stream().sorted((a,b)->
//            a.getDate().compareTo(b.getDate())
//        ).collect(Collectors.toList());
//
//        for (int i=0; i<priceList.size();i++) {
//            if(priceList.get(i).getDate().isBefore(LocalDate.now()) || priceList.get(i).getDate().isEqual(LocalDate.now())) {
//                productResponseDto.setSellingPrice(priceList.get(i).getPrice());
//                break;
//            }
//        }

        productResponseDto.setSellingPrice(latestBeforeToday.getPrice());

     return productResponseDto;
    }


//    public static Product convertProductRequestDtoToProduct(ProductRequestDto productRequestDto) {
//        Product product = new Product();
//        product.setName(productRequestDto.getName());
//        product.setImage(productRequestDto.getImage());
//        product.setDescription(productRequestDto.getDescription());
//        product.setCategory(productRequestDto.getCategory());
//        return product;
//    }

    public static List<ProductResponseDto> convertListOfProductsToProductResponseDtos(List<Product> products) {
        List<ProductResponseDto> response = new ArrayList<>();
        for (int i=0; i<products.size();i++) {
            response.add(convertProductToProductResponseDto(products.get(i)));
        }
        return response;
    }

}

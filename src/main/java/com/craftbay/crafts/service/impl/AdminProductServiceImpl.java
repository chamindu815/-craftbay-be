package com.craftbay.crafts.service.impl;

import com.craftbay.crafts.dto.product.AdminProductBuyingPriceDetailsDto;
import com.craftbay.crafts.dto.product.AdminProductSellingPriceDetailsDto;
import com.craftbay.crafts.dto.product.AdminUpdateProductRequestDto;
import com.craftbay.crafts.dto.product.AdminProductResponseDto;
import com.craftbay.crafts.entity.product.Product;
import com.craftbay.crafts.entity.product.ProductBuyingPriceDetails;
import com.craftbay.crafts.entity.product.ProductSellingPriceDetails;
import com.craftbay.crafts.repository.ProductRepository;
import com.craftbay.crafts.service.AdminProductService;
import com.craftbay.crafts.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class AdminProductServiceImpl implements AdminProductService {
    @Autowired
    private ProductRepository productRepository;

    public AdminProductResponseDto adminSaveProduct(MultipartFile image, String name, String description, double buyingPrice, double sellingPrice, int quantity, LocalDate date, String category){

        Product product = new Product();

        if (image != null) {
            String filename = StringUtils.cleanPath(image.getOriginalFilename());
            if (filename.contains( "..")){
                System.out.println("Not a valid image");
            }
            try {
                product.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        product.setName(name);
        product.setDescription(description);
        product.setCategory(category);
        product.setRemainingQuantity(quantity);

        LocalDate createdDate = LocalDate.now();
        product.setCreatedDate(createdDate);

        LocalDate updateDate = LocalDate.now();
        product.setUpdateDate(updateDate);

        ProductBuyingPriceDetails productBuyingPriceDetails = new ProductBuyingPriceDetails();
        productBuyingPriceDetails.setPrice(buyingPrice);
        productBuyingPriceDetails.setDate(date);
        productBuyingPriceDetails.setQuantity(quantity);

        ProductSellingPriceDetails productSellingPriceDetails = new ProductSellingPriceDetails();
        productSellingPriceDetails.setDate(LocalDate.now());
        productSellingPriceDetails.setPrice(sellingPrice);

        product.setProductBuyingPriceDetails(List.of(productBuyingPriceDetails));
        product.setProductSellingPriceDetails(List.of(productSellingPriceDetails));


        Product savedProduct = productRepository.save(product);
        AdminProductResponseDto response = ProductUtil.convertProductToAdminProductResponseDto(savedProduct);
        return response;


//        Product product = ProductUtil.convertProductRequestDtoToProduct(productRequestDto);
//        Product savedProduct  = productRepository.save(product);
//        ProductResponseDto response = ProductUtil.convertProductToProductResponseDto(product);
//        return response;


//        ProductResponseDto response = ProductUtil.convertProductToProductResponseDto(productRepository.save(product));
//        return response;

    }

    public List<AdminProductResponseDto> adminGetProducts(){
        List<Product> productList = productRepository.findAll();
        List<AdminProductResponseDto> responseList= new ArrayList<AdminProductResponseDto>();
        for (int i=0;i<productList.size();i++) {

            AdminProductResponseDto response = ProductUtil.convertProductToAdminProductResponseDto(productList.get(i));
            responseList.add(response);
        }
        return responseList;
    }

    public AdminProductResponseDto adminGetProductById(int id){
        AdminProductResponseDto response = ProductUtil.convertProductToAdminProductResponseDto(productRepository.findById(id).get());
        return response;
    }

    public AdminProductResponseDto adminGetProductByName(String name){
        Product product = productRepository.findByName(name);
        AdminProductResponseDto response = ProductUtil.convertProductToAdminProductResponseDto(product);
        return response;
    }

    @Override
    public List<AdminProductResponseDto> adminViewProducts() {
        List<Product> productList = productRepository.findAll();
        List<AdminProductResponseDto> adminProductResponseDtoList = new ArrayList<>();
        for (int i =0; i<productList.size(); i++){
            AdminProductResponseDto responseDto = ProductUtil.convertProductToAdminProductResponseDto(productList.get(i));
            adminProductResponseDtoList.add(responseDto);
        }
        return adminProductResponseDtoList;
    }

    public String adminDeleteProduct(int id){
        productRepository.deleteById(id);
        return "Product Removed ||" +id;
    }


    public AdminProductResponseDto adminUpdateProduct(AdminUpdateProductRequestDto adminUpdateProductRequestDto){
        Product existingProduct = productRepository.findById(adminUpdateProductRequestDto.getId()).orElse(null);
        existingProduct.setName(adminUpdateProductRequestDto.getName());
        existingProduct.setDescription(adminUpdateProductRequestDto.getDescription());
        existingProduct.setCategory(adminUpdateProductRequestDto.getCategory());

        existingProduct.setUpdateDate(LocalDate.now());
//        existingProduct.setPrice(product.getPrice());
//        existingProduct.setQuantity(product.getQuantity());
        for (int i = 0; i< adminUpdateProductRequestDto.getAdminProductBuyingPriceDetailsDtoList().size(); i++) {
            AdminProductBuyingPriceDetailsDto adminProductBuyingPriceDetailsDto = adminUpdateProductRequestDto.getAdminProductBuyingPriceDetailsDtoList().get(i);


            existingProduct.getProductBuyingPriceDetails().stream().findAny();
            Optional<ProductBuyingPriceDetails> result = existingProduct.getProductBuyingPriceDetails()
                    .stream().parallel()
                    .filter(prodPriceDetail -> prodPriceDetail.getId() == adminProductBuyingPriceDetailsDto.getId()).findAny();
//            result != null -> update result
//            else create new result.isPresent()
            if (result.isPresent()){
                ProductBuyingPriceDetails existingProductPriceDetails = result.get();
                existingProductPriceDetails.setPrice(adminProductBuyingPriceDetailsDto.getPrice());
                existingProductPriceDetails.setDate(adminProductBuyingPriceDetailsDto.getDate());
                existingProductPriceDetails.setQuantity(adminProductBuyingPriceDetailsDto.getQuantity());
            } else {
//                existingProduct.setName("Chcamindu");
                existingProduct.setRemainingQuantity(existingProduct.getRemainingQuantity() + adminProductBuyingPriceDetailsDto.getQuantity());
                ProductBuyingPriceDetails productBuyingPriceDetails1 = new ProductBuyingPriceDetails();
                productBuyingPriceDetails1.setId(adminProductBuyingPriceDetailsDto.getId());
                productBuyingPriceDetails1.setPrice(adminProductBuyingPriceDetailsDto.getPrice());
                productBuyingPriceDetails1.setDate(adminProductBuyingPriceDetailsDto.getDate());
                productBuyingPriceDetails1.setQuantity(adminProductBuyingPriceDetailsDto.getQuantity());
                existingProduct.getProductBuyingPriceDetails().add(productBuyingPriceDetails1);
            }

        }

        for (int i = 0; i< adminUpdateProductRequestDto.getAdminProductSellingPriceDetailsDtoList().size(); i++){
            AdminProductSellingPriceDetailsDto adminProductSellingPriceDetailsDto = adminUpdateProductRequestDto.getAdminProductSellingPriceDetailsDtoList().get(i);

            existingProduct.getProductSellingPriceDetails().stream().findAny();
            Optional<ProductSellingPriceDetails> result = existingProduct.getProductSellingPriceDetails()
                    .stream().parallel()
                    .filter(prodPriceDetail -> prodPriceDetail.getId() == adminProductSellingPriceDetailsDto.getId()).findAny();

            if (result.isPresent()){
                ProductSellingPriceDetails existingProductPriceDetails = result.get();
                existingProductPriceDetails.setDate(adminProductSellingPriceDetailsDto.getDate());
                existingProductPriceDetails.setPrice(adminProductSellingPriceDetailsDto.getPrice());
            }
            else {

                ProductSellingPriceDetails productSellingPriceDetails1 = new ProductSellingPriceDetails();
                productSellingPriceDetails1.setId(adminProductSellingPriceDetailsDto.getId());
                productSellingPriceDetails1.setDate(adminProductSellingPriceDetailsDto.getDate());
                productSellingPriceDetails1.setPrice(adminProductSellingPriceDetailsDto.getPrice());
                existingProduct.getProductSellingPriceDetails().add(productSellingPriceDetails1);
            }

        }

        Product updatedProduct = productRepository.save(existingProduct);
        AdminProductResponseDto response = ProductUtil.convertProductToAdminProductResponseDto(updatedProduct);
        return response;
    }

}

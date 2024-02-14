package com.craftbay.crafts.service.impl;

import com.craftbay.crafts.dto.product.ProductBuyingPriceDetailsDto;
import com.craftbay.crafts.dto.product.ProductSellingPriceDetailsDto;
import com.craftbay.crafts.dto.product.UpdateProductRequestDto;
import com.craftbay.crafts.dto.product.ProductResponseDto;
import com.craftbay.crafts.entity.product.Product;
import com.craftbay.crafts.entity.product.ProductBuyingPriceDetails;
import com.craftbay.crafts.entity.product.ProductSellingPriceDetails;
import com.craftbay.crafts.repository.ProductRepository;
import com.craftbay.crafts.service.ProductService;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDto saveProduct(MultipartFile image, String name, String description,  double buyingPrice, double sellingPrice, int quantity, LocalDate date, String category){

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
        ProductResponseDto response = ProductUtil.convertProductToProductResponseDto(savedProduct);
        return response;


//        Product product = ProductUtil.convertProductRequestDtoToProduct(productRequestDto);
//        Product savedProduct  = productRepository.save(product);
//        ProductResponseDto response = ProductUtil.convertProductToProductResponseDto(product);
//        return response;


//        ProductResponseDto response = ProductUtil.convertProductToProductResponseDto(productRepository.save(product));
//        return response;

    }

    public List<ProductResponseDto> getProducts(){
        List<Product> productList = productRepository.findAll();
        List<ProductResponseDto> responseList= new ArrayList<ProductResponseDto>();
        for (int i=0;i<productList.size();i++) {

            ProductResponseDto response = ProductUtil.convertProductToProductResponseDto(productList.get(i));
            responseList.add(response);
        }
        return responseList;
    }

    public ProductResponseDto getProductById(int id){
        ProductResponseDto response = ProductUtil.convertProductToProductResponseDto(productRepository.findById(id).get());
        return response;
    }

    public ProductResponseDto getProductByName(String name){
        Product product = productRepository.findByName(name);
        ProductResponseDto response = ProductUtil.convertProductToProductResponseDto(product);
        return response;
    }

    public String deleteProduct(int id){
        productRepository.deleteById(id);
        return "Product Removed ||" +id;
    }


    public ProductResponseDto updateProduct(UpdateProductRequestDto updateProductRequestDto){
        Product existingProduct = productRepository.findById(updateProductRequestDto.getId()).orElse(null);
        existingProduct.setName(updateProductRequestDto.getName());
        existingProduct.setDescription(updateProductRequestDto.getDescription());
        existingProduct.setCategory(updateProductRequestDto.getCategory());

        existingProduct.setUpdateDate(LocalDate.now());
//        existingProduct.setPrice(product.getPrice());
//        existingProduct.setQuantity(product.getQuantity());
        for (int i =0; i< updateProductRequestDto.getProductBuyingPriceDetailsDtoList().size();i++) {
            ProductBuyingPriceDetailsDto productBuyingPriceDetailsDto = updateProductRequestDto.getProductBuyingPriceDetailsDtoList().get(i);


            existingProduct.getProductBuyingPriceDetails().stream().findAny();
            Optional<ProductBuyingPriceDetails> result = existingProduct.getProductBuyingPriceDetails()
                    .stream().parallel()
                    .filter(prodPriceDetail -> prodPriceDetail.getId() == productBuyingPriceDetailsDto.getId()).findAny();
//            result != null -> update result
//            else create new result.isPresent()
            if (result.isPresent()){
                ProductBuyingPriceDetails existingProductPriceDetails = result.get();
                existingProductPriceDetails.setPrice(productBuyingPriceDetailsDto.getPrice());
                existingProductPriceDetails.setDate(productBuyingPriceDetailsDto.getDate());
                existingProductPriceDetails.setQuantity(productBuyingPriceDetailsDto.getQuantity());
            } else {
//                existingProduct.setName("Chcamindu");
                existingProduct.setRemainingQuantity(existingProduct.getRemainingQuantity() + productBuyingPriceDetailsDto.getQuantity());
                ProductBuyingPriceDetails productBuyingPriceDetails1 = new ProductBuyingPriceDetails();
                productBuyingPriceDetails1.setId(productBuyingPriceDetailsDto.getId());
                productBuyingPriceDetails1.setPrice(productBuyingPriceDetailsDto.getPrice());
                productBuyingPriceDetails1.setDate(productBuyingPriceDetailsDto.getDate());
                productBuyingPriceDetails1.setQuantity(productBuyingPriceDetailsDto.getQuantity());
                existingProduct.getProductBuyingPriceDetails().add(productBuyingPriceDetails1);
            }

        }

        for (int i = 0; i< updateProductRequestDto.getProductSellingPriceDetailsDtoList().size(); i++){
            ProductSellingPriceDetailsDto productSellingPriceDetailsDto = updateProductRequestDto.getProductSellingPriceDetailsDtoList().get(i);

            existingProduct.getProductSellingPriceDetails().stream().findAny();
            Optional<ProductSellingPriceDetails> result = existingProduct.getProductSellingPriceDetails()
                    .stream().parallel()
                    .filter(prodPriceDetail -> prodPriceDetail.getId() == productSellingPriceDetailsDto.getId()).findAny();

            if (result.isPresent()){
                ProductSellingPriceDetails existingProductPriceDetails = result.get();
                existingProductPriceDetails.setDate(productSellingPriceDetailsDto.getDate());
                existingProductPriceDetails.setPrice(productSellingPriceDetailsDto.getPrice());
            }
            else {

                ProductSellingPriceDetails productSellingPriceDetails1 = new ProductSellingPriceDetails();
                productSellingPriceDetails1.setId(productSellingPriceDetailsDto.getId());
                productSellingPriceDetails1.setDate(productSellingPriceDetailsDto.getDate());
                productSellingPriceDetails1.setPrice(productSellingPriceDetailsDto.getPrice());
                existingProduct.getProductSellingPriceDetails().add(productSellingPriceDetails1);
            }

        }

        Product updatedProduct = productRepository.save(existingProduct);
        ProductResponseDto response = ProductUtil.convertProductToProductResponseDto(updatedProduct);
        return response;
    }

}

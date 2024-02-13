package com.craftbay.crafts.service.impl;

import com.craftbay.crafts.dto.product.ProductRequestDto;
import com.craftbay.crafts.dto.product.ProductResponseDto;
import com.craftbay.crafts.entity.Product;
import com.craftbay.crafts.repository.ProductRepository;
import com.craftbay.crafts.service.ProductService;
import com.craftbay.crafts.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ProductIMPL implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDto saveProduct(MultipartFile image, String name, String description, double price, int quantity, String category ){

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
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategory(category);

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


    public ProductResponseDto updateProduct(Product product){
        Product existingProduct= productRepository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setCategory(product.getCategory());
        Product updatedProduct = productRepository.save(existingProduct);
        ProductResponseDto response = ProductUtil.convertProductToProductResponseDto(updatedProduct);
        return response;
    }

}

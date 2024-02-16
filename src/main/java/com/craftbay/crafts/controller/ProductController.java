package com.craftbay.crafts.controller;

import com.craftbay.crafts.dto.product.ProductResponseDto;
import com.craftbay.crafts.service.AdminProductService;
import com.craftbay.crafts.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class ProductController {

    @Autowired
    private AdminProductService adminProductService;
    private ProductService productService;

    @CrossOrigin
    @GetMapping("/product")
    public List<ProductResponseDto> findAllProducts() {
        return adminProductService.getProducts();
    }

    @GetMapping("/product/{id}")
    public ProductResponseDto findProductById(int id) {
        return adminProductService.getProductById(id);
    }

    @GetMapping("/productbyname/{name}")
    public ProductResponseDto findProductByName(String name) {
        return adminProductService.getProductByName(name);
    }

    @GetMapping("/getNewArrival")
    public List<ProductResponseDto> getNewArrival(){
        return productService.getNewArrival();
    }

}

package com.craftbay.crafts.controller;

import com.craftbay.crafts.dto.product.AdminProductResponseDto;
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
    public List<AdminProductResponseDto> findAllProducts() {
        return adminProductService.adminGetProducts();
    }

    @GetMapping("/product/{id}")
    public AdminProductResponseDto findProductById(int id) {
        return adminProductService.adminGetProductById(id);
    }

    @GetMapping("/productbyname/{name}")
    public AdminProductResponseDto findProductByName(String name) {
        return adminProductService.adminGetProductByName(name);
    }

    @GetMapping("/getNewArrival")
    public List<AdminProductResponseDto> getNewArrival(){
        return productService.getNewArrival();
    }

}

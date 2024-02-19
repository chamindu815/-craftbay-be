package com.craftbay.crafts.controller;

import com.craftbay.crafts.dto.product.AdminProductResponseDto;
import com.craftbay.crafts.dto.pub.product.ProductResponseDto;
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

    @Autowired
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

    @CrossOrigin
    @GetMapping("/getNewArrival")
    public List<ProductResponseDto> getNewArrival(){
        return productService.getNewArrival();
    }

    @GetMapping("/getShopProducts")
    public List<ProductResponseDto> getShopProducts( @RequestParam(value = "category") String category){
        return productService.getShopProducts(category);
    }

}

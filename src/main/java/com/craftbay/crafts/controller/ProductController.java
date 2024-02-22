package com.craftbay.crafts.controller;

import com.craftbay.crafts.dto.product.AdminProductResponseDto;
import com.craftbay.crafts.dto.pub.product.ProductResponseDto;
import com.craftbay.crafts.service.AdminProductService;
import com.craftbay.crafts.service.ProductService;
import com.craftbay.crafts.util.enums.ProductCategoryEnum;
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

    @GetMapping("/productbyname/{name}")
    public AdminProductResponseDto findProductByName(String name) {
        return adminProductService.adminGetProductByName(name);
    }

    /*
     * Get New Arrival Products By Category
     */
    @CrossOrigin
    @GetMapping("/getNewArrival")
    public List<ProductResponseDto> getNewArrival(){
        return productService.getNewArrival();
    }

    /*
     * Get only 5 Products By Category
     */
    @CrossOrigin
    @GetMapping("/getShopProducts")
    public List<ProductResponseDto> getShopProducts( @RequestParam(value = "category") ProductCategoryEnum category){
        return productService.getShopProducts(category);
    }


    /*
    * Get All Products By Category
     */
    @CrossOrigin
    @GetMapping("/getAllProductsByCategory")
    public List<ProductResponseDto> getAllProductsByCategory(@RequestParam(value = "category") ProductCategoryEnum category){
        return productService.getAllProductsByCategory(category);
    }

}

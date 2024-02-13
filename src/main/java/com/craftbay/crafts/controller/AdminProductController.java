package com.craftbay.crafts.controller;

import com.craftbay.crafts.dto.product.ProductResponseDto;
import com.craftbay.crafts.entity.product.Product;
import com.craftbay.crafts.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @CrossOrigin
    @PostMapping("/addProduct")
      public ProductResponseDto addProduct(@RequestParam(value = "image", required = false) MultipartFile image,
                               @RequestParam("name") String name,
                               @RequestParam(value = "description", required = false) String description,
                               @RequestParam("price") double price,
                               @RequestParam("quantity") int quantity,
                               @RequestParam(value = "category", required = false) String category){

        return productService.saveProduct(image, name, description, price, quantity, category);
    }


    @PutMapping("/update")
    public ProductResponseDto updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}

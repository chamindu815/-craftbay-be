package com.craftbay.crafts.controller;

import com.craftbay.crafts.dto.product.AdminUpdateProductRequestDto;
import com.craftbay.crafts.dto.product.AdminProductResponseDto;
import com.craftbay.crafts.service.AdminProductService;
import com.craftbay.crafts.util.enums.ProductCategoryEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private AdminProductService adminProductService;

//    @CrossOrigin
    @PostMapping("/addProduct")
      public AdminProductResponseDto addProduct(@RequestParam(value = "image", required = false) MultipartFile image,
                                                @RequestParam("name") String name,
                                                @RequestParam(value = "description", required = false) String description,
                                                @RequestParam("buyingPrice") double buyingPrice,
                                                @RequestParam("sellingPrice") double sellingPrice,
                                                @RequestParam("quantity") int quantity,
                                                @RequestParam("date") String date,
                                                @RequestParam(value = "category", required = false) ProductCategoryEnum category)
    {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);


        return adminProductService.adminSaveProduct(image, name, description, buyingPrice, sellingPrice, quantity, localDate, category);

    }

//    @CrossOrigin
    @PutMapping("/update")
    public AdminProductResponseDto updateProduct(@RequestBody AdminUpdateProductRequestDto adminUpdateProductRequestDto) {
        return adminProductService.adminUpdateProduct(adminUpdateProductRequestDto);
    }

//    @CrossOrigin
    @GetMapping("/adminViewProduct")
    public List<AdminProductResponseDto> viewProduct(){
        return adminProductService.adminViewProducts();
    }

//    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        return adminProductService.adminDeleteProduct(id);
    }

//    @CrossOrigin
    @GetMapping("/{id}")
    public AdminProductResponseDto findProductById(@PathVariable("id") int id) {
        return adminProductService.adminGetProductById(id);
    }
}



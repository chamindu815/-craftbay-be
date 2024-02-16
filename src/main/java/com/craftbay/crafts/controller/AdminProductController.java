package com.craftbay.crafts.controller;

import com.craftbay.crafts.dto.product.UpdateProductRequestDto;
import com.craftbay.crafts.dto.product.ProductResponseDto;
import com.craftbay.crafts.service.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private AdminProductService adminProductService;

    @CrossOrigin
    @PostMapping("/addProduct")
      public ProductResponseDto addProduct(@RequestParam(value = "image", required = false) MultipartFile image,
                               @RequestParam("name") String name,
                               @RequestParam(value = "description", required = false) String description,
                               @RequestParam("buyingPrice") double buyingPrice,
                               @RequestParam("sellingPrice") double sellingPrice,
                               @RequestParam("quantity") int quantity,
                               @RequestParam("date") String date,
                               @RequestParam(value = "category", required = false) String category)
    {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);


        return adminProductService.saveProduct(image, name, description, buyingPrice, sellingPrice, quantity, localDate, category);

    }


    @PutMapping("/update")
    public ProductResponseDto updateProduct(@RequestBody UpdateProductRequestDto updateProductRequestDto) {
        return adminProductService.updateProduct(updateProductRequestDto);
    }

    @GetMapping("/adminViewProduct")
    public List<ProductResponseDto> viewProduct(){
        return adminProductService.adminViewProduct();
    }

    @GetMapping("/getNewArrival")
    public List<ProductResponseDto> getNewArrival(){
        return adminProductService.getNewArrival();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        return adminProductService.deleteProduct(id);
    }


}



package com.craftbay.crafts.dto.product;

import com.craftbay.crafts.util.enums.ProductCategoryEnum;
import lombok.Data;

import javax.persistence.Lob;

@Data
public class AdminAddProductRequestDto {
    private int id;
    private String name;
    private String description;
    private ProductCategoryEnum category;
    private double buyingPrice;
    private double sellingPrice;
    private int quantity;

    @Lob
    private String image;
}

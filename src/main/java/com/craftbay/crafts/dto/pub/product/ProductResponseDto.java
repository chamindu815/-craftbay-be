package com.craftbay.crafts.dto.pub.product;

import com.craftbay.crafts.util.enums.ProductCategoryEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;

@Getter
@Setter
public class ProductResponseDto {
    private int id;
    private String name;
    private String description;
    private ProductCategoryEnum category;
    private int remainingQuantity;
    private double sellingPrice;
    private int rate;
    private int noOfRatings;


    @Lob
    private String image;
}

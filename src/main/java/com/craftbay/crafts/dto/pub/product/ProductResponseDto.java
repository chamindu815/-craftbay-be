package com.craftbay.crafts.dto.pub.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;

@Getter
@Setter
public class ProductResponseDto {
    private int id;
    private String name;
    private String description;
    private String category;
    private int remainingQuantity;
    private double sellingPrice;


    @Lob
    private String image;
}

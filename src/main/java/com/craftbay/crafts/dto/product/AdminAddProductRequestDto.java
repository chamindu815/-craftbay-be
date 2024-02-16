package com.craftbay.crafts.dto.product;

import lombok.Data;

import javax.persistence.Lob;

@Data
public class AdminAddProductRequestDto {
    private int id;
    private String name;
    private String description;
    private String category;
    private double buyingPrice;
    private double sellingPrice;
    private int quantity;

    @Lob
    private String image;
}
